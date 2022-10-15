package com.cjcode.projectMinTic.Services;

import javax.servlet.http.HttpSession;

public interface FrontService {
    String validateUser(String email, HttpSession session);
}
