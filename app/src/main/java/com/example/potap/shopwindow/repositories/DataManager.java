package com.example.potap.shopwindow.repositories;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.potap.shopwindow.dbObjects.Categories;
import com.example.potap.shopwindow.dbObjects.News;
import com.example.potap.shopwindow.dbObjects.Orders;
import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.interfaces.CategoriesDAO;
import com.example.potap.shopwindow.interfaces.NewsDAO;
import com.example.potap.shopwindow.interfaces.OrdersDAO;
import com.example.potap.shopwindow.interfaces.SneakersDAO;

@Database(entities = {Sneakers.class, Categories.class, News.class,Orders.class}, version = 2)
public abstract class DataManager extends RoomDatabase {

    public abstract SneakersDAO sneakersDAO();

    public abstract CategoriesDAO categoriesDAO();

    public abstract NewsDAO newsDAO();

    public abstract OrdersDAO ordersDAO();

    private static volatile DataManager INSTANCE;

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final SneakersDAO mDao;
        private final CategoriesDAO cDao;
        private final NewsDAO nDao;
        private final OrdersDAO oDAO;

        PopulateDbAsync(DataManager db) {
            mDao = db.sneakersDAO();
            cDao = db.categoriesDAO();
            nDao = db.newsDAO();
            oDAO = db.ordersDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            mDao.deleteAll();
            cDao.deleteAll();
            nDao.deleteAll();
            oDAO.deleteAll();

            Orders orders = new Orders("Reebook",5000,"Камуфляж","https://cdn-images.farfetch-contents.com/11/86/73/35/11867335_8846357_480.jpg",1,43);
            oDAO.insert(orders);

            orders = new Orders("Nike",6000,"Белый","https://c.static-nike.com/a/images/f_auto,b_rgb:f5f5f5,q_80,w_440/exia9iydaqjh0to4wyha/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8-air-monarch-4-pr-lC42Cv.jpg",1,43);
            oDAO.insert(orders);
            orders = new Orders("Nike",6000,"Белый","https://c.static-nike.com/a/images/f_auto,b_rgb:f5f5f5,q_80,w_440/exia9iydaqjh0to4wyha/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8-air-monarch-4-pr-lC42Cv.jpg",1,43);
            oDAO.insert(orders);
            orders = new Orders("Nike",6000,"Белый","https://c.static-nike.com/a/images/f_auto,b_rgb:f5f5f5,q_80,w_440/exia9iydaqjh0to4wyha/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8-air-monarch-4-pr-lC42Cv.jpg",1,43);
            oDAO.insert(orders);
            orders = new Orders("Nike",6000,"Белый","https://c.static-nike.com/a/images/f_auto,b_rgb:f5f5f5,q_80,w_440/exia9iydaqjh0to4wyha/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8-air-monarch-4-pr-lC42Cv.jpg",1,43);
            oDAO.insert(orders);

            News news = new News("Распродажа", "https://www.idf-dutyfree.com/wp-content/uploads/2018/01/Big-Sale-NEWS-1-1100x485.jpg");
            nDao.insert(news);

            news = new News("Тренды сезона", "https://media.gq.com/photos/59b2b427b10d5e3a537c8801/master/w_3000/balenciaga-sneakers-lede-2.jpg");
            nDao.insert(news);

            news = new News("Футбольная коллекция", "http://footballbootsguru.com/wp-content/uploads/2017/06/Adidas-Glitch-football-boots-4-of-the-colourways.jpg");
            nDao.insert(news);

            news = new News("Баскетбольные новинки", "https://img.purch.com/w/660/aHR0cDovL3d3dy5zcGFjZS5jb20vaW1hZ2VzL2kvMDAwLzAyNi80NzIvb3JpZ2luYWwvbmlrZV9ob3VzdG9uc25lYWtlcnMuanBn");
            nDao.insert(news);

            Categories categories = new Categories("Женщинам", "http://www.soyuz.ru/public/uploads/files/2/7222303/201808232307493f39dad469.jpg");
            cDao.insert(categories);

            categories = new Categories("Мужчинам", "https://img.tsn.ua/cached/1533907670/tsn-5c161a41b1f154cd63aedacab6e94568/thumbs/1340x530/7e/46/633a36ad3197707c94f7ae8291c0467e.jpeg");
            cDao.insert(categories);

            categories = new Categories("Детям", "https://vokrug.tv/pic/news/0/d/9/1/0d9168ac055a2b70cf02d5b9041e5252.jpg");
            cDao.insert(categories);

            categories = new Categories("Футбол", "https://i.sportarena.com/2016/08/GettyImages-592319380.jpg");
            cDao.insert(categories);

            categories = new Categories("Баскетбол", "https://static.ukrinform.com/photos/2018_07/thumb_files/630_360_1533021312-6086.jpg");
            cDao.insert(categories);

            Sneakers sneakers = new Sneakers(
                    "Adidas",
                    4500,
                    "Верх с мембраной GORE-TEX® защищает стопу от проникновения воды. Светоотражающие элементы улучшат видимость в условиях плохого освещения",
                    "Красный",
                    0,
                    false,
                    -1,
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw19d10e3e/zoom/F36202_01_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw6cc57f23/zoom/F36202_010_hover_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw1b39c2f4/zoom/F36202_05_standard.jpg?sh=840&strip=false&sw=840"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Adidas",
                    4500,
                    "Верх с мембраной GORE-TEX® защищает стопу от проникновения воды. Светоотражающие элементы улучшат видимость в условиях плохого освещения",
                    "Красный",
                    0,
                    true,
                    -1,
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw19d10e3e/zoom/F36202_01_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw6cc57f23/zoom/F36202_010_hover_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw1b39c2f4/zoom/F36202_05_standard.jpg?sh=840&strip=false&sw=840"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Nike",
                    3000,
                    "Nike Air Max 95 — классические низкие беговые кроссовки для мужчин, разработанные в 1995 году. Работая над внешним видом модели, дизайнер Серджио Лозано вдохновлялся анатомией человеческого тела.",
                    "Белый",
                    0,
                    false,
                    -1,
                    "https://c.static-nike.com/a/images/f_auto,b_rgb:f5f5f5,q_80,w_440/exia9iydaqjh0to4wyha/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8-air-monarch-4-pr-lC42Cv.jpg",
                    "https://c.static-nike.com/a/images/f_auto,b_rgb:f5f5f5,w_440/vjsleghax3228bpidanh/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8-air-monarch-4-pr-lC42Cv.jpg",
                    "https://c.static-nike.com/a/images/f_auto,b_rgb:f5f5f5,q_80,w_440/hceo3c2wio68caxjxjwq/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8-air-monarch-4-pr-lC42Cv.jpg"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "Камуфляж",
                    0,
                    false,
                    -1,
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "Камуфляж",
                    1,
                    false,
                    -1,
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Nike",
                    6000,
                    "Nike Air Max 95 — классические низкие беговые кроссовки для мужчин, разработанные в 1995 году. Работая над внешним видом модели, дизайнер Серджио Лозано вдохновлялся анатомией человеческого тела.",
                    "Белый",
                    1,
                    false,
                    -1,
                    "https://c.static-nike.com/a/images/f_auto,b_rgb:f5f5f5,q_80,w_440/exia9iydaqjh0to4wyha/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8-air-monarch-4-pr-lC42Cv.jpg",
                    "https://c.static-nike.com/a/images/f_auto,b_rgb:f5f5f5,w_440/vjsleghax3228bpidanh/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8-air-monarch-4-pr-lC42Cv.jpg",
                    "https://c.static-nike.com/a/images/f_auto,b_rgb:f5f5f5,q_80,w_440/hceo3c2wio68caxjxjwq/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8-air-monarch-4-pr-lC42Cv.jpg"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "Камуфляж",
                    1,
                    false,
                    -1,
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);
             sneakers = new Sneakers(
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "Камуфляж",
                    1,
                    false,
                     -1,
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "Камуфляж",
                    1,
                    true,
                    -1,
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "Камуфляж",
                    1,
                    false,
                    -1,
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "PREDATOR 19.1 FG",
                    14990,
                    "Если ты разбиваешь тактику соперника в пух и прах, то Predators как раз для тебя. Эти футбольные бутсы с поддерживающим трикотажным верхом и облегающим носком обеспечивают точность и идеальный контроль на газоне с высоким ворсом. Цепкое текстурное покрытие на мыске повышает точность передач.",
                    "Чёрный/Серый",
                    1,
                    false,
                    0,
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw04029179/zoom/F97529_01_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwa80c5b21/zoom/F97529_010_hover_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwa68692e2/zoom/F97529_05_standard.jpg?sh=840&strip=false&sw=840"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Nike Phantom Vision",
                    7990,
                    " Внутри новых бутс Nike Phantom находится встроенная сетка, которая считывает особенности стопы игрока, что придает невероятную посадку. Снаружи модели использован материал Flyknit, который придает невероятное сцепление и повышает уровень владения мячом. Невидимая снаружи шнуровка Ghost Lace обеспечивает оптимальный контакт с мячом.",
                    "Чёрный",
                    1,
                    false,
                    0,
                    "https://render.nikeid.com/ir/render/nikeidrender/ACADEMYFA18_v1?obj=/s/shadow/shad&show&color=000000&obj=/s/g1&color=141414&show&obj=/s/g2&color=141414&show&obj=/s/g7&color=141414&show&obj=/s/g14&color=141414&show&obj=/s/g16&color=141414&show&obj=/s/g3&color=1a1b1b&show&obj=/s/g5&color=212121&show&obj=/s/g4/solid&color=dafd17&show&obj=/s/g6&color=dafd17&show&obj=/s/g8/tlogo&color=dafd17&show&obj=/s/g13&color=212121&show&obj=/s/g9/mg&color=404040&show&obj=/s/g15&color=141414&show&obj=/s&req=object&fmt=png-alpha&icc=AdobeRGB&wid=1500",
                    "https://render.nikeid.com/ir/render/nikeidrender/ACADEMYFA18_v2?obj=/s/shadow/shad&show&color=000000&obj=/s/g1&color=141414&show&obj=/s/g2&color=141414&show&obj=/s/g7&color=141414&show&obj=/s/g14&color=141414&show&obj=/s/g16&color=141414&show&obj=/s/g3&color=1a1b1b&show&obj=/s/g5&color=212121&show&obj=/s/g4/solid&color=dafd17&show&obj=/s/g6&color=dafd17&show&obj=/s/g8/tlogo&color=dafd17&show&obj=/s/g13&color=212121&show&obj=/s/g9/mg&color=404040&show&obj=/s/g15&color=141414&show&obj=/s&req=object&fmt=png-alpha&icc=AdobeRGB&wid=1500",
                    "https://render.nikeid.com/ir/render/nikeidrender/ACADEMYFA18_v6?obj=/s/shadow/shad&show&color=000000&obj=/s/g1&color=141414&show&obj=/s/g2&color=141414&show&obj=/s/g7&color=141414&show&obj=/s/g14&color=141414&show&obj=/s/g16&color=141414&show&obj=/s/g3&color=1a1b1b&show&obj=/s/g5&color=212121&show&obj=/s/g4/solid&color=dafd17&show&obj=/s/g6&color=dafd17&show&obj=/s/g8/tlogo&color=dafd17&show&obj=/s/g13&color=212121&show&obj=/s/g9/mg&color=404040&show&obj=/s/g15&color=141414&show&obj=/s&req=object&fmt=png-alpha&icc=AdobeRGB&wid=1500"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "PREDATOR 19.1 TR",
                    9990,
                    "Соберись и действуй. Или у тебя нет шансов. Predators созданы для тех, кто контролирует поле. Эти футбольные кроссовки повторяют дизайн культовых бутс той же модели. Трикотажный верх плотно облегает стопу и обеспечивает устойчивость",
                    "Белый",
                    1,
                    false,
                    0,
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw3d5f711f/zoom/F35848_01_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw399c05cb/zoom/F35848_010_hover_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwb349990c/zoom/F35848_05_standard.jpg?sh=840&strip=false&sw=840"
                    );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "PREDATOR 19.1 FG",
                    7990,
                    "Эти футбольные бутсы с цепким текстурным покрытием буквально приклеивают мяч к ноге. Модель подходит для газона с высоким ворсом. Сетчатый верх анатомической конструкции",
                    "Чёрный",
                    1,
                    false,
                    0,
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwebc47cb1/zoom/D97997_01_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwa29fc590/zoom/D97997_02_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw82de7c3d/zoom/D97997_05_standard.jpg?sh=840&strip=false&sw=840"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers("Nike Mercurial Superfly VI",
                    7990,
                    "Футбольные бутсы для игры на твердом грунте Nike Mercurial Superfly V обеспечивают отличную посадку, непревзойденное касание и потрясающее сцепление, позволяя развивать максимальную скорость на полях с короткой травой. Технология Dynamic Fit в области голеностопа обеспечивает плотную посадку и комфорт.",
                    "Красный",
                    1,
                    false,
                    0,
                    "https://render.nikeid.com/ir/render/nikeidrender/SFLY6ACAD_v1?obj=/s/shadow/shad&show&color=000000&obj=/s/g1&color=d83745&show&obj=/s/g2&color=da0b3a&show&obj=/s/g3&color=da0b3a&show&obj=/s/g4/met&color=dbdbdb&show&obj=/s/g6/met&color=dbdbdb&show&obj=/s/g7&color=252525&show&obj=/s/g10&color=ff3126&show&obj=/s/g5&color=141414&show&obj=/s/g9&color=ced4e1&show&obj=/s/g8/mg&color=d83745&show&obj=/s/g14&color=141414&show&obj=/s&req=object&fmt=png-alpha&icc=AdobeRGB&wid=1500",
                    "https://render.nikeid.com/ir/render/nikeidrender/SFLY6ACAD_v4?obj=/s/shadow/shad&show&color=000000&obj=/s/g1&color=d83745&show&obj=/s/g2&color=da0b3a&show&obj=/s/g3&color=da0b3a&show&obj=/s/g4/met&color=dbdbdb&show&obj=/s/g6/met&color=dbdbdb&show&obj=/s/g7&color=252525&show&obj=/s/g10&color=ff3126&show&obj=/s/g5&color=141414&show&obj=/s/g9&color=ced4e1&show&obj=/s/g8/mg&color=d83745&show&obj=/s/g14&color=141414&show&obj=/s&req=object&fmt=png-alpha&icc=AdobeRGB&wid=1500",
                    "https://render.nikeid.com/ir/render/nikeidrender/SFLY6ACAD_v2?obj=/s/shadow/shad&show&color=000000&obj=/s/g1&color=d83745&show&obj=/s/g2&color=da0b3a&show&obj=/s/g3&color=da0b3a&show&obj=/s/g4/met&color=dbdbdb&show&obj=/s/g6/met&color=dbdbdb&show&obj=/s/g7&color=252525&show&obj=/s/g10&color=ff3126&show&obj=/s/g5&color=141414&show&obj=/s/g9&color=ced4e1&show&obj=/s/g8/mg&color=d83745&show&obj=/s/g14&color=141414&show&obj=/s&req=object&fmt=png-alpha&icc=AdobeRGB&wid=1500"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "N3XT L3V3L",
                    13990,
                    "Уходи от соперников в этих баскетбольных кроссовках без шнурков. Плотно облегающий трикотажный верх надежно поддерживает стопу со всех сторон. Промежуточная подошва из пены Lightstrike обеспечивает оптимальную амортизацию на площадке.",
                    "Чёрный",
                    1,
                    false,
                    1,
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw9babb836/zoom/BB9194_01_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw2a44d743/zoom/BB9194_010_hover_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw5880efdf/zoom/BB9194_05_standard.jpg?sh=840&strip=false&sw=840"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "PRO BOUNCE MADNESS LOW 2019",
                    7990,
                    "Вдохновляйся энергией Кардиналс в этих баскетбольных кроссовках, созданных для маневренной игры. Текстильный верх с мягким голенищем надежно поддерживает щиколотку, а упругая промежуточная подошва амортизирует прыжки и дарит комфорт на площадке.",
                    "Красный",
                    1,
                    false,
                    1,
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwf39583c9/zoom/BB9283_01_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw2ade6775/zoom/BB9283_010_hover_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw07126efc/zoom/BB9283_05_standard.jpg?sh=840&strip=false&sw=840"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "STREETFLOW",
                    6990,
                    "Низкие баскетбольные кроссовки в стиле поздних 90-х. Модель из мягкого нубука с прочной подошвой. Упругая подошва с дополнительной вставкой из пенного материала под пяткой эффективно амортизирует каждый шаг.",
                    "Серый",
                    1,
                    false,
                    1,
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwd526cd24/zoom/F36619_01_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw106bdcf2/zoom/F36619_010_hover_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwd61debcf/zoom/F36619_05_standard.jpg?sh=840&strip=false&sw=840"
            );
            mDao.insert(sneakers);

            return null;
        }
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    static DataManager getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataManager.class, "shopDB")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
