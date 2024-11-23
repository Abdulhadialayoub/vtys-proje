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
API_KEY = '28fe54216ff44003b561aa34d8663623'
coins = [
    'bitcoin', 'ethereum', 'binance coin', 'cardano', 'ripple',
    'solana', 'polkadot', 'dogecoin', 'chainlink', 'litecoin',
    'bitcoin cash', 'stellar', 'uniswap', 'polygon', 'vechain',
    'theta', 'cosmos', 'tron', 'avalanche', 'algorand',

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

# 1. Boş değerleri kontrol et ve temizle
print(news_df.isnull().sum())
news_df = news_df.dropna(subset=['description'])

# 2. Metin verilerini ve etiketleri ayır
X = news_df['description']
y = news_df['coin']

# 3. Veriyi eğitim ve test setlerine ayır
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# 4. TF-IDF Vektörleştirme
vectorizer = TfidfVectorizer()
X_train_tfidf = vectorizer.fit_transform(X_train)
X_test_tfidf = vectorizer.transform(X_test)

# 5. Modeli oluştur ve eğit
model = LogisticRegression(max_iter=1000)
model.fit(X_train_tfidf, y_train)

# 6. Tahmin yapın
y_pred = model.predict(X_test_tfidf)

# 7. Doğruluk oranını hesaplayın
accuracy = accuracy_score(y_test, y_pred)
print(f'Doğruluk Oranı: {accuracy * 100:.2f}%')

# 8. Daha fazla bilgi için sınıflandırma raporu
print(classification_report(y_test, y_pred, zero_division=0))

# GPU mevcut mu kontrol et
device = 0 if torch.cuda.is_available() else -1

# Model ve tokenizer ayarları
gpt_model = pipeline('text-generation', model='gpt2', device=device)
gpt_model.tokenizer.pad_token_id = gpt_model.tokenizer.eos_token_id  # Pad token ayarını yap


# clean_up_tokenization_spaces ve truncation ayarları
def generate_advice(coin, description):
    input_text = f"Yatırımcılar için {coin} ile ilgili şu makale: '{description}'. Bu bilgiye dayanarak yatırım tavsiyesi ver."
    response = gpt_model(input_text, max_length=70, num_return_sequences=1, truncation=True,
                         clean_up_tokenization_spaces=False)
    return response[0]['generated_text'].strip()


# Her bir coin için tahminleri yazdır
print("\nGelecekteki Tahminler:")
for coin in coins:
    coin_news = news_df[news_df['coin'] == coin]
    coin_descriptions = coin_news['description']

    # Tahminleri almak için açıklamaları dönüştür
    coin_descriptions_tfidf = vectorizer.transform(coin_descriptions)
    coin_predictions = model.predict(coin_descriptions_tfidf)

    positive_count = sum(1 for pred in coin_predictions if pred == coin)
    negative_count = len(coin_predictions) - positive_count

    print(f"\n{coin.upper()} için Tahminler:")
    if positive_count > negative_count:
        advice = generate_advice(coin, "Positive predictions based on market trends.")
        print(advice)
    else:
        advice = generate_advice(coin, "Negative predictions based on market trends.")
        print(advice)

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
        model = LogisticRegression(
            max_iter=1000)  # max_iter değerini artırarak modelin daha iyi öğrenmesini sağlayabilirsiniz
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

                engine = create_engine('sqlite:///result.db')
                Base = declarative_base()


                # Prediction modeli tanımı
                class Prediction(Base):
                    __tablename__ = 'predictions'
                    id = Column(Integer, primary_key=True)
                    coin = Column(String)
                    prediction = Column(String)
                    positive_count = Column(Integer)
                    negative_count = Column(Integer)
                    risk_score = Column(Float)  # Yeni sütun
                    date = Column(DateTime, default=datetime.datetime.utcnow)


                # Veritabanını oluştur
                Base.metadata.create_all(engine)
                Session = sessionmaker(bind=engine)
                session = Session()


                # Risk skorunu hesaplama fonksiyonu
                def calculate_risk_score(positive_count, negative_count):
                    total_count = positive_count + negative_count
                    if total_count == 0:
                        return 0.5  # Veri yoksa nötr bir değer döndür
                    risk_score = negative_count / total_count
                    return round(risk_score, 2)


                # Veritabanına ve CSV'ye kaydetme fonksiyonu
                def save_to_database_and_csv(coin, prediction, positive_count, negative_count, risk_score):
                    # Veritabanına kaydet
                    new_prediction = Prediction(
                        coin=coin,
                        prediction=prediction,
                        positive_count=positive_count,
                        negative_count=negative_count,
                        risk_score=risk_score  # Risk skorunu ekle
                    )
                    session.add(new_prediction)
                    session.commit()

                    # CSV'ye kaydet
                    df = pd.DataFrame([{
                        'coin': coin,
                        'prediction': prediction,
                        'positive_count': positive_count,
                        'negative_count': negative_count,
                        'risk_score': risk_score,
                        'date': datetime.datetime.utcnow()
                    }])
                    df.to_csv('predictions.csv', mode='a', header=False, index=False)  # Mode 'a' yeni veriyi ekler


                # CSV dosyasına başlık eklemek için başlangıçta bir başlık yazalım (eğer dosya yoksa)
                try:
                    pd.read_csv('predictions.csv')
                except FileNotFoundError:
                    df_empty = pd.DataFrame(
                        columns=['coin', 'prediction', 'positive_count', 'negative_count', 'risk_score', 'date'])
                    df_empty.to_csv('predictions.csv', index=False)

                # Her coin için tahminleri ve risk skorunu kaydet
                for coin in coins:
                    coin_news = news_df[news_df['coin'] == coin]

                    # Boş kontrolü yap
                    if coin_news.empty:
                        print(f"{coin} için veri bulunamadı. Atlanıyor...")
                        continue

                    coin_descriptions = coin_news['description']
                    coin_descriptions_tfidf = vectorizer.transform(coin_descriptions)
                    coin_predictions = model.predict(coin_descriptions_tfidf)

                    positive_count = sum(1 for pred in coin_predictions if pred == coin)
                    negative_count = len(coin_predictions) - positive_count
                    prediction_result = "Gelecek için olumlu, yatırım yapın." if positive_count > negative_count else "Gelecek için olumsuz, yatırım yapmayın."

                    # Risk skorunu hesapla
                    risk_score = calculate_risk_score(positive_count, negative_count)

                    # Veritabanına ve CSV'ye kaydet
                    save_to_database_and_csv(coin, prediction_result, positive_count, negative_count, risk_score)

                print("Tüm tahminler ve risk skorları veritabanına ve CSV'ye kaydedildi.")