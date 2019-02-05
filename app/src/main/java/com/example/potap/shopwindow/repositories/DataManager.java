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

@Database(entities = {Sneakers.class, Categories.class, News.class,Orders.class}, version = 1)
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
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "Камуфляж",
                    1,
                    false,
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Adidas",
                    4500,
                    "Верх с мембраной GORE-TEX® защищает стопу от проникновения воды. Светоотражающие элементы улучшат видимость в условиях плохого освещения",
                    "Красный",
                    0,
                    false,
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw19d10e3e/zoom/F36202_01_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw6cc57f23/zoom/F36202_010_hover_standard.jpg?sh=840&strip=false&sw=840",
                    "https://www.adidas.ru/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw1b39c2f4/zoom/F36202_05_standard.jpg?sh=840&strip=false&sw=840"
            );
            mDao.insert(sneakers);
            sneakers = new Sneakers(
                    "Nike",
                    6000,
                    "Nike Air Max 95 — классические низкие беговые кроссовки для мужчин, разработанные в 1995 году. Работая над внешним видом модели, дизайнер Серджио Лозано вдохновлялся анатомией человеческого тела.",
                    "Белый",
                    1,
                    true,
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
                    0,
                    false,
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
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
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
                            DataManager.class, "shop")
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
