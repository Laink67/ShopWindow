package com.example.potap.shopwindow.repositories;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.interfaces.SneakersDAO;

@Database(entities = {Sneakers.class}, version = 1)
public abstract class DataManger extends RoomDatabase {
    public abstract SneakersDAO sneakersDAO();

    private static volatile DataManger INSTANCE;

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final SneakersDAO mDao;

        PopulateDbAsync(DataManger db) {
            mDao = db.sneakersDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            mDao.deleteAll();

            Sneakers sneakers = new Sneakers(
                    "Reebook",
                    5000,
                    "Износостойкая подошва прослужит много километров, а промежуточная подошва из термополиуретана будет эффективно поглощать энергию удара.",
                    "http://u8.filesonload.ru/eea2d99d18b0568e86cb408aaf1f1cea/acbff98b95d87c867601cf14eb2ff148.jpg",
                    "http://www.sneakerwatch.com/images/size_fs/video-026242.jpg",
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

            return null;
        }
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    static DataManger getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataManger.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataManger.class, "sneakers")
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
