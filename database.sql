CREATE TABLE tp_user (uid INTEGER PRIMARY KEY NOT NULL,username TEXT NOT NULL,password TEXT NOT NULL ,email TEXT NOT NULL);
CREATE TABLE tp_file (id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT NOT NULL,filename TEXT NOT NULL ,filehash TEXT NOT NULL,blockhash TEXT NOT NULL);