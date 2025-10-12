package com.example.framework.utils;

import jakarta.mail.*;
import jakarta.mail.search.SubjectTerm;

import java.util.Properties;

public class EmailUtils {
    private final String host;
    private final String user;
    private final String pass;
    private final String folder;

    public EmailUtils(String host, String user, String pass, String folder) {
        this.host = host; this.user = user; this.pass = pass; this.folder = folder;
    }

    public Message[] searchBySubject(String subject) {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");
            Session session = Session.getInstance(props);
            Store store = session.getStore();
            store.connect(host, user, pass);
            Folder f = store.getFolder(folder);
            f.open(Folder.READ_ONLY);
            Message[] msgs = f.search(new SubjectTerm(subject));
            f.close(false);
            store.close();
            return msgs;
        } catch (Exception e) {
            throw new RuntimeException("Email search failed", e);
        }
    }
}
