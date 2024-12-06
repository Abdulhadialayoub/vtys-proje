package com.rf.coin_analysis.config;

import com.rf.coin_analysis.model.Predictions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Component
public class Initializer {
    public static List<String> positiveSentences = List.of(
            "Bu coin, piyasa koşullarında güçlü bir performans sergileyerek gelecekte değer kazanabilecek bir yapıya sahip.",
            "Coin'in gelişimi, sektördeki güçlü oyuncuların desteğiyle daha da hızlanabilir, bu da yatırımcılar için büyük bir fırsat sunar.",
            "Gelecekte, bu coin'in kabul oranı arttıkça daha fazla yatırımcı çekmesi ve piyasa değerinin yükselmesi bekleniyor.",
            "Yatırımcılar için cazip bir seçenek sunan bu coin, teknolojik inovasyonlar sayesinde önemli bir büyüme gösterebilir.",
            "Coin'in arkasındaki ekip, sektördeki deneyimiyle dikkat çekiyor ve yenilikçi özellikler sunarak değer kazanabilir.",
            "Blockchain teknolojisinin sağladığı avantajlarla bu coin, gelecekte daha fazla kullanım alanına sahip olabilir.",
            "Bu coin, topluluk tarafından büyük ilgi görmekte ve gelecekte piyasada önemli bir yer edinebilir.",
            "İleriye dönük güçlü projeler ve gelişmelerle, bu coin’in değer kazanması yüksek olasılıkla gerçekleşecektir.",
            "Coin’in sunduğu teknolojik yenilikler, yatırımcılar için büyük bir fırsat yaratmakta ve uzun vadeli değer kazanımı sağlayabilir.",
            "Bu coin’in sektördeki konumu giderek güçleniyor, bu da yatırımcılar için cazip fırsatlar doğuruyor.",
            "Coin'in arkasındaki ekip yenilikçi projeler geliştirmekte, bu da coin’in gelecekte değer kazanmasını sağlayacaktır.",
            "Teknolojik altyapısı güçlü olan bu coin, piyasa trendlerine ayak uyduracak ve değeri hızla artacaktır.",
            "Coin’in kullanım alanlarının genişlemesi, gelecekte yatırımcılar için önemli kazançlar sağlayabilir.",
            "Bu coin'in potansiyeli, piyasa talebine ve teknolojik gelişmelere paralel olarak büyük bir artış gösterebilir.",
            "Gelecekte, bu coin'in daha fazla platformda kullanılmaya başlanması bekleniyor ve bu da değer kazanımını hızlandıracaktır.",
            "Coin’in yüksek güvenlik önlemleri, yatırımcıların bu coine olan güvenini artırarak değer kazanmasına yol açabilir.",
            "Yüksek likidite ve güçlü kullanıcı tabanı, bu coinin uzun vadede değer kazanma potansiyelini artırmaktadır.",
            "Bu coin, sektördeki rekabetin artmasına rağmen, sağladığı yeniliklerle ön plana çıkmaya devam edecektir.",
            "Coin'in tasarımı ve işlevselliği, gelecekteki piyasa eğilimlerine mükemmel bir uyum sağlamaktadır.",
            "Bu coin'in gelecekteki başarılı iş ortaklıkları, değerinin daha da artmasına katkı sağlayacaktır.",
            "Yatırımcılar için düşük riskli bir seçenek olan bu coin, gelecekte sağlam kazançlar getirebilir.",
            "Piyasadaki gelişmelere hızla adapte olabilen bu coin, gelecekte büyük kazançlar elde edebilir.",
            "Coin’in değer kazanma potansiyeli, kullanıcıların ve yatırımcıların ilgisini artırarak büyümeye devam edecektir.",
            "Bu coin, blockchain alanındaki yenilikçi projeleri ile kısa vadede önemli kazançlar elde edebilir.",
            "Coin'in gelişen ekosistemi ve güçlü altyapısı, gelecekteki başarısını garanti altına alacaktır.",
            "Coin'in sağladığı özellikler, kullanıcıların bu coini tercih etmelerini sağlayarak değerinin artmasına yol açacaktır.",
            "Bu coin, sektördeki inovatif yaklaşımlarıyla yatırımcılar için büyük bir fırsat sunuyor.",
            "Blockchain altyapısının sağladığı avantajlarla, bu coin'in gelecekte önemli bir yer edineceği öngörülmektedir.",
            "Yatırımcıların ilgisi arttıkça, bu coin'in değeri hızla yükselebilir ve güçlü bir piyasa oluşturabilir.",
            "Bu coin’in gelecekteki kullanım alanları genişledikçe, yatırımcılar için kazançlı bir fırsat olabilir.",
            "Yatırımcıların ilgisini çeken bu coin, teknolojik altyapısı ve sürdürülebilir büyümesi ile dikkat çekiyor.",
            "Coin’in başarısı, teknolojik gelişmelerle desteklenen sağlam temellere dayanmaktadır.",
            "Gelecekte daha fazla kullanıcıya ulaşan bu coin, değerini arttırarak yatırımcılara önemli kazançlar sağlayabilir.",
            "Coin’in gelişen altyapısı ve artan talep ile gelecekte yüksek kazançlar sağlanması bekleniyor.",
            "Bu coin’in pazardaki hakimiyeti güçleniyor ve bu da değerinin artmasına yol açabilir.",
            "Yüksek potansiyel ve güvenlik önlemleri ile bu coin’in gelecekteki değeri önemli ölçüde artabilir.",
            "Coin’in yenilikçi özellikleri, yatırımcıların ilgisini çekerken piyasa değeri artışını da hızlandırabilir.",
            "Bu coin, sektördeki lider konumunu pekiştirecek yeni projelerle değer kazanacaktır.",
            "Yatırımcılar için cazip fırsatlar sunan bu coin, gelişen teknoloji ve artan taleple değer kazanabilir.",
            "Coin’in gelecekteki değeri, yenilikçi projelerle ve kullanıcı tabanının genişlemesiyle artacaktır.",
            "Bu coin’in geleceği, sağlam temeller ve güçlü destekle güvence altına alınmıştır."
    );
   public static List<String> negativeSentences = List.of(
            "Bu coin'in risk faktörleri yüksek ve gelecekte değer kaybı yaşayabilir.",
            "Piyasa koşulları göz önüne alındığında, bu coin'in değeri düşüş gösterebilir.",
            "Coin'in geleceği, belirsizliklerle dolu ve kısa vadede olumsuz etkilenebilir.",
            "Bu coin’in arkasındaki ekip, yeterince güçlü bir stratejiye sahip değil gibi görünüyor.",
            "Coin'in piyasa talebi, yeterince istikrarlı olmadığı için değeri düşebilir.",
            "Coin'in blockchain altyapısı, önemli güvenlik sorunları barındırmakta ve bu da gelecekteki değerini olumsuz etkileyebilir.",
            "Bu coin, piyasa koşullarına yeterince adapte olamayabilir ve değer kaybı yaşayabilir.",
            "Yatırımcılar, bu coin’e güven duymayabilir ve bu da piyasadaki değerini düşürebilir.",
            "Coin’in arkasındaki teknoloji, mevcut piyasa koşullarıyla uyumlu görünmüyor ve bu da değer kaybına yol açabilir.",
            "Yatırımcıların ilgisini çekmeyen bu coin, kısa vadede değer kaybı yaşayabilir.",
            "Bu coin’in geleceği, belirsiz bir şekilde şekilleniyor ve risk faktörleri yüksek.",
            "Coin’in gelişimi, sektördeki zorluklarla karşı karşıya kalabilir ve piyasa değerini olumsuz etkileyebilir.",
            "Coin’in likidite oranı düşük olduğu için piyasada yeterli alıcı bulmak zor olabilir.",
            "Bu coin, yüksek volatilite nedeniyle yatırımcılar için riskli bir seçenek olabilir.",
            "Coin'in güvenliği yeterince sağlam değil, bu da uzun vadede değer kaybına neden olabilir.",
            "Coin’in gelecekteki potansiyeli, piyasa koşulları nedeniyle sınırlı olabilir.",
            "Bu coin, piyasa talebine hızla adapte olamayabilir ve değeri zamanla düşebilir.",
            "Coin’in değeri, kullanıcı sayısının azalmaya başlamasıyla birlikte hızla düşebilir.",
            "Coin’in fiyatı, gelecekteki olumsuz piyasa koşulları nedeniyle değer kaybedebilir.",
            "Coin’in kullanım alanı sınırlı olduğu için, talep azaldıkça değeri de düşebilir.",
            "Bu coin’in değerinin düşmesi, teknik sorunlar ve piyasa belirsizliklerinden kaynaklanabilir.",
            "Yatırımcılar bu coin’e şüpheyle yaklaşabilir ve bu da değer kaybına yol açabilir.",
            "Bu coin’in güvenlik açıkları, yatırımcıların ilgisini kaybetmesine neden olabilir.",
            "Coin’in geleceği, daha fazla engelle karşılaşabilir ve bu da değerinin düşmesine yol açabilir.",
            "Coin’in teknolojik yapısı, gelişen piyasa ihtiyaçlarına karşı yetersiz kalabilir.",
            "Bu coin, piyasada rekabetle karşı karşıya kalabilir ve bu da değer kaybına yol açabilir.",
            "Coin’in fiyatı, sektördeki belirsizlikler nedeniyle olumsuz etkilenebilir.",
            "Bu coin’in geleceği, büyük riskler taşıyor ve değeri düşebilir.",
            "Yatırımcılar bu coin’e olan güveni kaybedebilir ve bu da değerinin düşmesine yol açabilir.",
            "Coin’in arkasındaki ekip yeterli tecrübeye sahip değilse, piyasa talebini karşılayamayabilir.",
            "Bu coin, gelişen piyasa şartlarına uyum sağlayamayabilir ve değer kaybedebilir.",
            "Coin’in değeri, gelecekteki olumsuz ekonomik koşullardan olumsuz etkilenebilir.",
            "Bu coin’in değeri, yüksek işlem ücretleri nedeniyle yatırımcılar için cazip olmayabilir.",
            "Coin’in tasarımı, kullanıcıların ihtiyaçlarını karşılamayacak kadar yetersiz kalabilir.",
            "Bu coin, teknolojik gerilik nedeniyle gelecekte değer kaybedebilir.",
            "Coin’in piyasadaki rekabet gücü, zayıf olabilir ve değeri düşebilir.",
            "Bu coin’in güvenlik protokolleri yetersiz olduğu için yatırımcıları riske atabilir.",
            "Coin’in yatırımcılar için cazip olmayan özellikleri, değer kaybına yol açabilir.",
            "Coin’in değeri, piyasa dalgalanmaları nedeniyle sürekli olarak değişebilir.",
            "Coin’in geleceği, gelişen teknolojilere uyum sağlamadığı takdirde olumsuz etkilenebilir.",
            "Coin’in geleceği, zayıf ekonomik temellere dayanarak tehlikeye girebilir.",
            "Bu coin, gelecekteki piyasa krizlerinden olumsuz etkilenebilir ve değeri düşebilir.",
            "Coin’in altyapısı yetersiz kaldığı takdirde, gelecekte değer kaybı yaşanabilir."
    );


    public static String getRandomDescription(double riskScore) {
        Random rand = new Random();
        if (riskScore < 0.50) {
            return positiveSentences.get(rand.nextInt(positiveSentences.size()));
        } else {
            return negativeSentences.get(rand.nextInt(negativeSentences.size()));
        }
    }
}
