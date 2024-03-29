package com.tiny1.util;

import com.tiny1.configuration.Conf;
import com.tiny1.model.Defaults;


public class ArgParser {
    private final String[] args;

    public ArgParser(String[] args) {
        this.args = args;
    }

    public void parse() {

        for (int i = 0; i < args.length; i++) {
            try {
                i = handleArg(i);
            } catch (Exception e) {
                System.out.println("Invalid arguments. See --help");
                System.exit(0);
            }
        }

        Conf.makeImmutable();

    }

    private int handleArg(int index) throws NumberFormatException {

        String param = args[index].toLowerCase();

        switch (param) {
            case "-p":
            case "--port":
                Conf.setPort(Integer.parseInt(args[++index]));
                break;
            case "-v":
            case "--version":
                System.out.println(Defaults.VERSION);
                System.exit(0);
                break;
            case "-ps":
            case "--pool-size":
                Conf.setPoolSize(Integer.parseInt(args[++index]));
                break;
            case "-hs":
            case "--header-size":
                Conf.setHeaderSize(Integer.parseInt(args[++index]));
                break;
            case "-dr":
            case "--document-root":
                Conf.setStaticPath(args[++index]);
                break;
            case "--tls":
                Conf.setTLS(true);
                break;
            case "-r":
            case "--redirect":
                Conf.addRedirects(args[++index],args[++index]);
                break;
            case "-d":
            case "--debug":
                Conf.setDebug(true);
                break;
            case "-h":
            case "--help":
                Console.showHelp();
                System.exit(0);
        }
        return index;

    }
}
