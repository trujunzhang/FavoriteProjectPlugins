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

        List<String> paths = new LinkedList<String>();

        SaxParseProjectService.FavoriteList favoriteList = null;
        /**
         * 保存具体内容，下次自动离线加载
         */
        SaxParseProjectService sax = new SaxParseProjectService();
        try {
            InputStream is = new FileInputStream(xmlPath);
            favoriteList = sax.parse(is);
            paths = getList(favoriteList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paths;
    }

    private static List<String> getList(SaxParseProjectService.FavoriteList favoriteList) {
        List<String> projectPaths = new LinkedList<String>();

        List<String> favoritePaths = favoriteList.favoritePaths;
        HashMap<String, String> favoriteEnviron = favoriteList.favoriteEnviron;

        for (String key : favoriteEnviron.keySet()) {
            projectPaths = new LinkedList<String>();
            String value = favoriteEnviron.get(key);
            for (String path : favoritePaths) {
                String token = String.format("${%s}", key);
                projectPaths.add(path.replace(token, value));
            }
            favoritePaths = projectPaths;
        }

        return favoritePaths;
    }


}
