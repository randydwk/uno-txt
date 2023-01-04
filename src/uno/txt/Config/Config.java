package uno.txt.Config;

import uno.txt.Uno;
import uno.txt.Utils;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class Config {
    static String filePath = "config.properties";

    public static void initConfig(){
        File f = new File(filePath);
        if(!f.exists()) {
            createConfigFile();
        }
    }

    private static void createConfigFile(){
        try {
            File file = new File(filePath);
            file.createNewFile();
            FileWriter myWriter = new FileWriter(filePath);
            for(Options options : Options.values()){
                myWriter.write(options.getKey() + "=" + options.getParameters() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Erreur au moment de la création du fichier configuration");
            e.printStackTrace();
        }
    }

    public static String getStringProperties(Options options){
        return getParameters(options);
    }

    public static void setStringProperties(Options options, String res){
        try {
            FileInputStream propsInput = new FileInputStream(filePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            prop.setProperty(options.getKey(), res);
            FileWriter writer = new FileWriter(filePath);
            prop.store(writer, "");
            writer.close();
            System.out.println("Valeur changé");
        } catch (FileNotFoundException e) {
            System.err.println("Impossible de trouver le fichier de configuration");
        } catch (IOException e) {
            System.err.println("IOException sur le fichier config");
        }
    }

    public static boolean getBooleanProperties(Options options) {
        String res = "";
        res = getParameters(options);
        return Boolean.getBoolean(res);
    }

    public static void initConfigMenu(){
        int i = 1;
        HashMap<Integer, Options> optionList = new HashMap<Integer, Options>();
        System.out.println("=================[ Configuration ]=================");
        for (Options config : Options.values()){
            optionList.put(i, config);
            System.out.println(i + " - " + config.getKey() + " : " + Config.getStringProperties(config));
            i++;
        }
        System.out.println("Merci de choisir une option");
        int res = Utils.getIntInput();
        System.out.println("Modifier la valeur de " + optionList.get(res) + " par :");
        String resStr = Utils.getStringInput();
        Config.setStringProperties(optionList.get(res), resStr);
        Uno.mainMenu();
    }

    private static String getParameters(Options options) {
        String res = "";
        try {
            FileInputStream propsInput = new FileInputStream(filePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            res = prop.getProperty(options.getKey());
            if (res == null) {
                System.err.println("Impossible de trouver la clé " + options.getKey() + " dans le fichier de configuration");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Impossible de trouver le fichier de configuration");
        } catch (IOException e) {
            System.err.println("IOException sur le fichier config");
        }
        return res;
    }
}
