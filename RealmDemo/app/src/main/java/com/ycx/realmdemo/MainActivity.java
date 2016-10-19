package com.ycx.realmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getInstance(
                new RealmConfiguration.Builder(this)
                        .name("myOtherRealm.realm")
                        .build()
        );
        Log.i("lxm", "..............");


    }

    public void insert(View view) {
        insert();

    }

    public void findAll(View view) {
        findAll();

    }

    public void modify(View view) {
        modify();
    }

    public void delete(View view) {
        delete();
    }


    private void insert() {
        /**********************************/
        realm.beginTransaction();

        City country1 = realm.createObject(City.class);

        country1.setName("Norway");
        country1.setCount(100);
        country1.setCityId("201610181734");

        realm.commitTransaction();

        /********************************/
        // Create the object
        City country2 = new City();
        country2.setName("Russia");
        country2.setCount(146430430);
        country2.setCityId("201610181736");

        realm.beginTransaction();
        realm.copyToRealm(country2);
        realm.commitTransaction();
    }


    private void findAll() {
        RealmResults<City> results1 =
                realm.where(City.class).findAll();

        for (City c : results1) {
            Log.i("lxm", c.getName());
        }

        RealmResults<City> results2 = realm.where(City.class)
                .equalTo("name", "Russia")
                .findAll();
        for (City c : results2) {
            Log.i("lxm", "......." + c.getName());
        }
    }

    private void modify(){
        realm.beginTransaction();
        RealmResults<City> results = realm.where(City.class).findAll();

        if (results.size() == 0) return;
        City city = results.get(0);
        city.setName("lxm");
        realm.copyToRealmOrUpdate(city);
        realm.commitTransaction();
    }

    private void delete(){
        realm.beginTransaction();
        RealmResults results = realm.where(City.class).findAll();
        //方式一：按下标删除
        if (results.size() == 0)return;
        results.remove(0);
        ////方式二：删除指定model第一种
        //Book book = new Book();
        //book.setId(2);
        //book.setName("史记");
        //book.setAuthor("司牛迁");
        //results.remove(book);
        //方式三：删除指定model第一种
        //book.removeFromRealm();
        ////方式四：删除末尾一个
        //results.removeLast();
        ////方式wu：清除全部
        //results.clear();
        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
