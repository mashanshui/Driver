/**
 * Copyright (c) 2015, Spencer , ChinaSunHZ (www.spencer-dev.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shenhesoft.driver.utils;

import android.app.Activity;

import java.util.ArrayList;

/**
 * activity manager tool
 * Created by Spencer (www.spencer-dev.com) on 15/2/3.
 */
public class ActivityManager {

    private ActivityManager() {

    }

    private static ArrayList<Activity> actList = new ArrayList<Activity>();
    private static ArrayList<Activity> commonNewsAcList = new ArrayList<Activity>();

    /**
     * close all activity
     */
    public synchronized static void closeAllActivity() {
        int size = actList.size();
        for (int i = 0; i < size; i++) {
            Activity activity = actList.get(i);
            if (activity != null) {
                activity.finish();
            }
        }

        int commSize = commonNewsAcList.size();
        for(int i = 0; i < commSize; i++){
            Activity activity = commonNewsAcList.get(i);
            if(activity != null){
                activity.finish();
            }
        }


        ExitProcessThread exitProcessThread = new ExitProcessThread();
        exitProcessThread.setName("ExitProcessThread");
        exitProcessThread.start();

    }

    public static void addActivity(Activity activity) {
        if(activity.getClass().getSimpleName().equals("CommonNewsWebViewActivity")){
            commonNewsAcList.add(activity);
            if(commonNewsAcList.size() > 5){
                commonNewsAcList.get(0).finish();
            }
        }else{
            actList.add(activity);
        }
    }
    public static void removeActivity(Activity activity) {
        if(activity.getClass().getSimpleName().equals("CommonNewsWebViewActivity")){
            commonNewsAcList.remove(activity);
        }else {
            actList.remove(activity);
        }
    }

    public synchronized static ArrayList<Activity> getAllActivity() {
        return actList;
    }

    public synchronized static boolean hasActivity(Class clazz) {
        boolean result = false;
        int activitySize = actList.size();
        for (int i = 0; i < activitySize; i++) {
            System.out.println(actList.get(i).getClass().getSimpleName());
            if (actList.get(i).getClass().equals(clazz)) {
                result = true;
                break;
            }
        }

        return result;
    }


    private static class ExitProcessThread extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            android.os.Process.killProcess(android.os.Process.myPid());

            System.exit(0);
        }
    }

}