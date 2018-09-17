package com.example.laakso.deutschlernen;

import android.content.Context;
import android.content.res.TypedArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ResourceHelper {



    public static List<TypedArray> getMultiTypedArray(Context context, String key, boolean[] includes) {
        List<TypedArray> array = new ArrayList<>();


        try {
            Class<R.array> res = R.array.class;
            Field field;
            int counter = 0;

            do {
                field = res.getField(key + "_" + counter);


                if (includes[counter] ) {
                    array.add(context.getResources().obtainTypedArray(field.getInt(null)));
                }

                counter++;
            } while (field != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;

    }

}
