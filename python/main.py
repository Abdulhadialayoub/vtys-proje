import requests
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score, classification_report
from transformers import pipeline
import torch

# API anahtarınızı buraya girin
# API_KEY = 'acd46a8ada904c7b83a7fa96882954b5'
API_KEY= '28fe54216ff44003b561aa34d8663623'
coins = [
    'bitcoin', 'ethereum', 'binance coin', 'cardano', 'ripple',
    'solana', 'polkadot', 'dogecoin', 'chainlink', 'litecoin',
    'bitcoin cash', 'stellar', 'uniswap', 'polygon', 'vechain'
]
all_news = []

for coin in coins:
    url = f'https://newsapi.org/v2/everything?q={coin}&language=en&apiKey={API_KEY}'
    response = requests.get(url)
    data = response.json()

    if data['status'] == 'ok':
        articles = data['articles']
        for article in articles:
            all_news.append({
                'coin': coin,
                'title': article['title'],
                'description': article['description'],
                'url': article['url'],
                'publishedAt': article['publishedAt']
            })

# Haberleri bir DataFrame'e kaydet
news_df = pd.DataFrame(all_news)
print(news_df.head())

# Haberleri bir DataFrame'e kaydet
news_df = pd.DataFrame(all_news)

# 1. Boş değerleri kontrol et ve temizle
print(news_df.isnull().sum())  # Her sütundaki boş değerlerin sayısını gösterir
news_df = news_df.dropna(subset=['description'])  # Boş açıklamaları kaldır

# 2. Metin verilerini ve etiketleri ayır
X = news_df['description']  # Açıklama sütununu kullan
y = news_df['coin']  # Coin etiketlerini kullan

# 3. Veriyi eğitim ve test setlerine ayır
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# 4. TF-IDF Vektörleştirme
vectorizer = TfidfVectorizer()
X_train_tfidf = vectorizer.fit_transform(X_train)
X_test_tfidf = vectorizer.transform(X_test)  # Test verisini dönüştür

# 5. Modeli oluştur ve eğit
model = LogisticRegression(max_iter=1000)  # max_iter değerini artırarak modelin daha iyi öğrenmesini sağlayabilirsiniz
model.fit(X_train_tfidf, y_train)

# 6. Tahmin yapın
y_pred = model.predict(X_test_tfidf)

# 7. Doğruluk oranını hesaplayın
accuracy = accuracy_score(y_test, y_pred)
print(f'Doğruluk Oranı: {accuracy * 100:.2f}%')

# 8. Daha fazla bilgi için sınıflandırma raporu
print(classification_report(y_test, y_pred))

# 9. Her coin için tahminleri yazdır
print("\nGelecekteki Tahminler:")
for coin in coins:
    # Her bir coin için test verisinde tahmin yap
    coin_news = news_df[news_df['coin'] == coin]
    coin_descriptions = coin_news['description']
    coin_descriptions_tfidf = vectorizer.transform(coin_descriptions)
    coin_predictions = model.predict(coin_descriptions_tfidf)

    # Tahminlerin sayısını ve her coin için tahmin sonuçlarını yazdır
    positive_count = sum(1 for pred in coin_predictions if pred == coin)  # Olumlu tahmin sayısı
    negative_count = len(coin_predictions) - positive_count  # Olumsuz tahmin sayısı

    print(f"\n{coin.upper()} için Tahminler:")
    if positive_count > negative_count:
        print("Sonuç: Gelecek için olumlu, yatırım yapın.")
    else:
        print("Sonuç: Gelecek için olumsuz, yatırım yapmayın.")


from sqlalchemy import Column, Integer, String, DateTime, create_engine
from sqlalchemy.orm import declarative_base, sessionmaker
import pandas as pd
import datetime

# SQLAlchemy veritabanı ayarları
engine = create_engine('sqlite:///predictions.db')
Base = declarative_base()

# Prediction modeli tanımı
class Prediction(Base):
    __tablename__ = 'predictions'
    id = Column(Integer, primary_key=True)
    coin = Column(String)
    prediction = Column(String)
    positive_count = Column(Integer)
    negative_count = Column(Integer)
    date = Column(DateTime, default=datetime.datetime.utcnow)

# Veritabanını oluştur
Base.metadata.create_all(engine)
Session = sessionmaker(bind=engine)
session = Session()

# Tahminleri veritabanına kaydetme
def save_to_database(coin, prediction, positive_count, negative_count):
    new_prediction = Prediction(
        coin=coin,
        prediction=prediction,
        positive_count=positive_count,
        negative_count=negative_count
    )
    session.add(new_prediction)
    session.commit()

# Her coin için tahminleri kaydet
for coin in coins:
    coin_news = news_df[news_df['coin'] == coin]
    coin_descriptions = coin_news['description']
    coin_descriptions_tfidf = vectorizer.transform(coin_descriptions)
    coin_predictions = model.predict(coin_descriptions_tfidf)

    positive_count = sum(1 for pred in coin_predictions if pred == coin)
    negative_count = len(coin_predictions) - positive_count
    prediction_result = "Gelecek için olumlu, yatırım yapın." if positive_count > negative_count else "Gelecek için olumsuz, yatırım yapmayın."

    # Veritabanına kaydet
    save_to_database(coin, prediction_result, positive_count, negative_count)

print("Tüm tahminler veritabanına kaydedildi.")