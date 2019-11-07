package utils;

import javax.servlet.http.HttpServletRequest;


public class IpUtil {

    /**
     * @param request
     * @return
     * @description: 如果通过了多级反向代理的话，
     * X-Forwarded-For的值并不止一个， 而是一串IP值，
     * 取 X-Forwarded-For中第一个非unknown的有效IP字符串。
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
