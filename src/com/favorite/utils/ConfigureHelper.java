package com.favorite.utils;

import com.intellij.openapi.project.Project;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: djzhang
 * Date: 9/18/13
 * Time: 12:38 AM
 * To change this template use File | Settings | File Templates.
 */
public final class ConfigureHelper {


    public static List<String> getPaths(String xmlPath, Project project) {

        /**
         * 保存具体内容，下次自动离线加载
         */
        SaxParseProjectService sax = new SaxParseProjectService();
        try {
            InputStream is = new FileInputStream(xmlPath);
            SaxParseProjectService.FavoriteList favoriteList = sax.parse(is);
            List<String> favoritePaths = favoriteList.favoritePaths;
            HashMap<String, String> favoriteEnviron = favoriteList.favoriteEnviron;
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> paths = new LinkedList<String>();

        return paths;
    }

}
