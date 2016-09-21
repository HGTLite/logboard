package com.hgt.serialize;

import java.io.*;

/******************************************************************************
 * Created by  Yao  on  2016/7/12
 * README:
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class PersonSerializer {

    public void serialize2file() {

        String filename = "person.txt";
        File file = new File(filename);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(fos);
                Person person = new Person("King", 22);
                System.out.println("Person对象序列化："+person);
                oos.writeObject(person);
                //写入对象
                oos.flush();
                System.out.println("写入成功");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println("oos关闭失败：" + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件：" + e.getMessage());
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                System.out.println("fos关闭失败：" + e.getMessage());
            }
        }




    }

    public void deserialize2bean(String path) {

        File file = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(fis);
                try {
                    Person person = (Person) ois.readObject();
                    //从文件读出对象
                    System.out.println("从文件反序列化得到的对象是：" + person);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("ois关闭失败：" + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件：" + e.getMessage());
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println("fis关闭失败：" + e.getMessage());
            }
        }

    }

}






