# Coin Analiz Projesi  

## Projenin Amacı  
Coin Analiz Projesi, kullanıcıların kripto paralara yatırım kararlarını desteklemek amacıyla geliştirilmiştir. Bu proje, kripto paralarla ilgili haberleri analiz ederek, her bir coin için duygu analizi gerçekleştirir ve bir risk skoru sunar. Ayrıca kullanıcıların coinleri favorilere ekleyip kendi portföylerini görüntüleyebilecekleri bir sistem sağlar.  

---

## Kullanılacak Teknolojiler  

### Backend  
#### **Python**  
- Haberlerin duygu analizini gerçekleştirmek ve risk skorunu hesaplamak için kullanılacak.  
- **Kullanılacak Kütüphaneler**:  
  - `RandomForestClassifier`  
  - `LogisticRegression`  
  - `Pandas`  
  - `NLTK` veya `TextBlob` gibi duygu analizi kütüphaneleri.  

#### **MySQL**  
- Risk skorları ve analiz sonuçları gibi verilerin saklanacağı ilişkisel veritabanı olarak kullanılacak.  

#### **Spring Boot**  
- API geliştirmek, kullanıcı doğrulama ve yetkilendirme işlemleri, favori coinlerin yönetimi gibi işlemleri gerçekleştirmek için kullanılacak.  
- **Özellikler**:  
  - Kullanıcı Girişi/Çıkışı  
  - Kullanıcı Kayıt  
  - Favori Coinleri Ekleme/Çıkarma  
  - Favori Coinleri Görüntüleme  

---

### Frontend  
#### **React**  
- Kullanıcı dostu bir arayüz geliştirmek için kullanılacak.  
- **Özellikler**:  
  - Kullanıcı Girişi ve Kaydı  
  - Coin analiz sonuçlarını görüntüleme  
  - Favori coin listesini yönetme  
  - Risk skorlarını ve haber analizlerini görsel grafiklerle sunma  

---

### API  
#### **News API**  
- Coinlerle ilgili haberleri çekmek için kullanılacak.  

---

## Genel Akış  
1. **Haber Çekme**:  
   - News API ile coinlerle ilgili haberler toplanır.  

2. **Duygu Analizi ve Risk Skoru Hesaplama**:  
   - Python ile haberlerin duygu analizi yapılır.  
   - Her bir coin için olumlu/olumsuz analiz sonuçlarına göre bir risk skoru hesaplanır.  

3. **Veri Depolama**:  
   - Analiz sonuçları önce bir CSV dosyasına kaydedilir.  
   - Daha sonra MySQL veritabanına aktarılır.  

4. **Spring Boot API**:  
   - Verilere erişim ve yönetim için bir REST API geliştirilir.  
   - Kullanıcı girişi, çıkışı, favori coinleri ekleme/çıkarma işlemleri gerçekleştirilir.  

5. **Frontend (React)**:  
   - Kullanıcılar analiz sonuçlarını, risk skorlarını ve haberleri burada görüntüler.  
   - Kullanıcıların favori coinlerini ekleyip görüntüleyebilecekleri bir arayüz sunulur.  

---

## Projenin Özellikleri  
- **Giriş/Çıkış ve Kullanıcı Kayıt**:  
  Kullanıcılar hesap oluşturup giriş yapabilir.  

- **Haber Analizi**:  
  Haberlerden alınan duygu analizi sonuçlarıyla coinler için risk skorları oluşturulur.  

- **Favori Coin Yönetimi**:  
  Kullanıcılar favori coinlerini ekleyip kaldırabilir ve kendi portföylerini görebilir.  

- **Veri Görselleştirme**:  
  Risk skorları ve analiz sonuçları kullanıcı dostu grafiklerle sunulur.  

---

## Proje Durumu  
- [x] News API entegrasyonu  
- [x] Duygu analizi modülü  
- [x] Risk skoru hesaplama  
- [x] Spring Boot API geliştirme  
- [x] MySQL veritabanı bağlantısı  
- [x] Kullanıcı Giriş/Çıkış ve Kayıt  
- [x] Favori coin yönetimi  
- [x] React arayüz tasarımı  

---

## Katkıda Bulunmak  
Bu projeye katkıda bulunmak için bir **pull request** oluşturabilir veya issue açabilirsiniz.  

---

## Spring Boot API Endpoints

### **Coin Controller**  
- **GET /api/coins/{id}**:  
  - Coin bilgilerini getirir.  
  - **Parametre**: `id` (Coin ID)  
  - **Örnek**: `/api/coins/1`

- **GET /api/coins**:  
  - Coin listesini getirir.  
  - **Örnek**: `/api/coins`

- **GET /api/coins/order-by-asc**:  
  - Risk skoruna göre en düşükten en yükseğe sırasıyla coinleri getirir.  
  - **Örnek**: `/api/coins/order-by-asc`

- **GET /api/coins/{name}/price**:  
  - Belirli bir coinin fiyatını döndürür.  
  - **Parametre**: `name` (Coin adı)  
  - **Örnek**: `/api/coins/bitcoin/price`

### **Favorite Controller**  
- **POST /api/favorites/{coinID}/{userId}**:  
  - Kullanıcı favorilerine coin ekler.  
  - **Parametreler**: `coinID`, `userId`  
  - **Örnek**: `/api/favorites/1/101`

- **DELETE /api/favorites/{id}**:  
  - Kullanıcının favorilerinden coin çıkarır.  
  - **Parametre**: `id` (Favori coin ID)  
  - **Örnek**: `/api/favorites/1`

- **GET /api/favorites/{userId}**:  
  - Kullanıcının favori coin listesini getirir.  
  - **Parametre**: `userId`  
  - **Örnek**: `/api/favorites/101`

### **User Controller**  
- **POST /api/users/register**:  
  - Yeni bir kullanıcı kaydeder.  
  - **Body**: `UserRegisterRequest`  
  - **Örnek**: `/api/users/register`

- **DELETE /api/users/{id}**:  
  - Kullanıcıyı siler.  
  - **Parametre**: `id` (User ID)  
  - **Örnek**: `/api/users/101`

- **GET /api/users/{id}**:  
  - Kullanıcı bilgilerini getirir.  
  - **Parametre**: `id` (User ID)  
  - **Örnek**: `/api/users/101`

### **Auth Controller**  
- **POST /api/auth/login**:  
  - Kullanıcı giriş yapar ve bir token alır.  
  - **Body**: `LoginRequest`  
  - **Örnek**: `/api/auth/login`  
  - **Başarılı yanıt**: Kullanıcıya ait bir **login-token** döndürülür.

- **DELETE /api/auth/logout**:  
  - Kullanıcı çıkış yapar ve **login-token** silinir.  
  - **Örnek**: `/api/auth/logout`  

---

## Kurulum  

### Backend  
1. **Python**:  
   - Gerekli kütüphaneleri yüklemek için:  
     ```bash
     pip install -r requirements.txt
     ```  
   - Python scriptlerini çalıştırın ve analiz sonuçlarını üretin.  

2. **Spring Boot**:  
   - Spring Boot uygulamasını başlatın:  
     ```bash
     mvn spring-boot:run
     ```  

3. **MySQL**:  
   - Veritabanını oluşturun ve yapılandırmaları sağlayın.  

---

### Frontend  
1. **React**:  
   - Gerekli bağımlılıkları yüklemek için:  
     ```bash
     npm install
     ```  
   - React uygulamasını çalıştırın:  
     ```bash
     npm start
     ```  

---

Bu projeye katkıda bulunmak isteyen herkese teşekkürler!
