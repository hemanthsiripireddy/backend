 class MySingleton {

    // volatile is mandatory to prevent instruction reordering
    private static volatile MySingleton instance;

    private MySingleton() {}

    public static MySingleton getInstance() {
        if (instance == null) {                         // 1st check (no locking)
            synchronized (MySingleton.class) {          // lock only when needed
                if (instance == null) {                 // 2nd check (with locking)
                    instance = new MySingleton();
                }
            }
        }
        return instance;
    }
}
//