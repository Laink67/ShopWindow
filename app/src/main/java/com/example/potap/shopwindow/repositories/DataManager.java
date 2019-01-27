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
import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.interfaces.CategoriesDAO;
import com.example.potap.shopwindow.interfaces.NewsDAO;
import com.example.potap.shopwindow.interfaces.SneakersDAO;

@Database(entities = {Sneakers.class, Categories.class, News.class}, version = 3)
public abstract class DataManager extends RoomDatabase {

    public abstract SneakersDAO sneakersDAO();
    public abstract CategoriesDAO categoriesDAO();
    public abstract NewsDAO newsDAO();

    private static volatile DataManager INSTANCE;

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final SneakersDAO mDao;
        private final CategoriesDAO cDao;
        private final NewsDAO nDao;

        PopulateDbAsync(DataManager db) {
            mDao = db.sneakersDAO();
            cDao = db.categoriesDAO();
            nDao = db.newsDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            mDao.deleteAll();
            cDao.deleteAll();
            nDao.deleteAll();

            News news = new News("Распродажа","https://www.idf-dutyfree.com/wp-content/uploads/2018/01/Big-Sale-NEWS-1-1100x485.jpg");
            nDao.insert(news);

            news = new News("Тренды сезона","https://media.gq.com/photos/59b2b427b10d5e3a537c8801/master/w_3000/balenciaga-sneakers-lede-2.jpg");
            nDao.insert(news);

            news = new News("Футбольная коллекция", "http://footballbootsguru.com/wp-content/uploads/2017/06/Adidas-Glitch-football-boots-4-of-the-colourways.jpg");
            nDao.insert(news);

            news = new News ("Баскетбольные новинки","https://img.purch.com/w/660/aHR0cDovL3d3dy5zcGFjZS5jb20vaW1hZ2VzL2kvMDAwLzAyNi80NzIvb3JpZ2luYWwvbmlrZV9ob3VzdG9uc25lYWtlcnMuanBn");
            nDao.insert(news);

            Categories categories = new Categories("Новые товары", "http://www.slamdunk.ru/images/j/jordans.jpg");
            cDao.insert(categories);

            Sneakers sneakers = new Sneakers(
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);

            sneakers = new Sneakers(
                    "Adidas",
                    4500,
                    "Верх с мембраной GORE-TEX® защищает стопу от проникновения воды. Светоотражающие элементы улучшат видимость в условиях плохого освещения",
                    "http://greezzlee.ru/wp-content/uploads/2018/01/Adidas-prophere-8.jpg",
                    "http://greezzlee.ru/wp-content/uploads/2018/01/Adidas-prophere-3-1.jpg",
                    "http://greezzlee.ru/wp-content/uploads/2018/01/Adidas-prophere-5-1.jpg"
            );
            mDao.insert(sneakers);
            sneakers = new Sneakers(
                    "Nike",
                    6000,
                    "Nike Air Max 95 — классические низкие беговые кроссовки для мужчин, разработанные в 1995 году. Работая над внешним видом модели, дизайнер Серджио Лозано вдохновлялся анатомией человеческого тела.",
                    "http://greezzlee.ru/wp-content/uploads/2018/09/Nike-air-max-95.jpg",
                    "http://greezzlee.ru/wp-content/uploads/2018/09/Nike-air-max-95-2-2.jpg",
                    "http://greezzlee.ru/wp-content/uploads/2018/09/Nike-air-max-95-2-2.jpg"
            );
            mDao.insert(sneakers);
            sneakers = new Sneakers(
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);
            sneakers = new Sneakers(
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);
            sneakers = new Sneakers(
                    "Reebook5",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);
            sneakers = new Sneakers(
                    "Reebook3",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);
            sneakers = new Sneakers(
                    "Reebook2",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
                    "https://www.reebok.co.nz/dis/dw/image/v2/AAJP_PRD/on/demandware.static/-/Sites-reebok-products/default/dwd2261105/zoom/BD4221_01.jpg?sh=600&strip=false"
            );
            mDao.insert(sneakers);
            sneakers = new Sneakers(
                    "Reebook1",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
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
                            DataManager.class, "shop_db")
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
