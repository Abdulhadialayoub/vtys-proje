# Coin Analiz Projesi  

**Projenin Amacı**  
Bu proje, kullanıcıların hangi coinlere yatırım yapıp yapmamaları gerektiği konusunda destek sağlamayı amaçlamaktadır. Yapay zeka ve duygu analizi yöntemlerini kullanarak coinlere dair haberleri analiz eder, olumlu ve olumsuz cümleleri değerlendirir ve bir risk skoru sunar.  

## Kullanılacak Teknolojiler  

### Backend  
- **Python**  
  - Veri analizi ve makine öğrenmesi için kullanılacak.  
  - **Kullanılacak Kütüphaneler**:  
    - `RandomForestClassifier`  
    - `LogisticRegression`  
  - Coinlerle ilgili haberlerin analizinde kullanılacak.  

- **MySQL**  
  - Haber analizlerinden elde edilen sonuçlar ve risk skorları önce **CSV** dosyasına kaydedilecek, ardından MySQL veritabanına aktarılacak.  

- **Spring Boot**  
  - MySQL'deki verileri işlemek ve API servisi sunmak için kullanılacak.  
  - **Security** ile güvenlik sağlanacak.  

### Frontend  
- **React**  
  - Kullanıcı arayüzü için kullanılacak.  
  - Kullanıcılar, risk skorlarını ve analiz sonuçlarını buradan görüntüleyebilecek.  

### API  
- **News API**  
  - Coinlerle ilgili haberlerin çekilmesi için kullanılacak.  

## Genel Akış  
1. News API ile coin haberleri toplanır.  
2. Python ile bu haberlerin duygu analizi yapılır.  
   - Olumlu ve olumsuz cümleler tespit edilir.  
   - Risk skoru hesaplanır.  
3. Veriler CSV dosyasına kaydedilir ve MySQL veritabanına aktarılır.  
4. Spring Boot ile API oluşturularak bu verilere erişim sağlanır.  
5. React ile kullanıcıya sonuçlar gösterilir.  

## Proje Durumu  
- [ ] Yapay zeka analiz modülü  
- [ ] Duygu analizi entegrasyonu  
- [ ] Haber API bağlantısı  
- [ ] Veritabanı entegrasyonu  
- [ ] Backend API geliştirme  
- [ ] Frontend arayüz geliştirme  

## Katkıda Bulunmak  
Bu projeye katkıda bulunmak için bir **pull request** oluşturabilir veya issue açabilirsiniz.  
