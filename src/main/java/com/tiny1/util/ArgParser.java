package com.tiny1.util;

import com.tiny1.model.Conf;
import com.tiny1.model.Defaults;


public class ArgParser {
    private final String[] args;

    public ArgParser(String[] args) {
        this.args = args;
    }

    public void parse() {
        if (args.length == 0)
            return;

        for (int i = 0; i<args.length;i++) {
            try {
                handleArg(i);
            } catch (Exception e) {
                System.out.println("Invalid arguments. See --help");
                System.exit(0);
            }
        }

    }

    private void handleArg(int index) throws NumberFormatException {

        String param = args[index];

        switch (param) {
            case "-p", "--port" -> Conf.port = Integer.parseInt(args[index + 1]);
            case "-v", "--version" -> {
                System.out.println(Defaults.VERSION);
                System.exit(0);
            }
            case "-ps", "--pool-size" -> Conf.poolSize = Integer.parseInt(args[index + 1]);
            case "-hs", "--header-size" -> Conf.headerSize = Integer.parseInt(args[index + 1]);
            case "-sp", "--static-path" -> Conf.staticPath = args[index+1];
            case "-h", "--help" -> {
                Console.showHelp();
                System.exit(0);
            }
        }

    }
}
