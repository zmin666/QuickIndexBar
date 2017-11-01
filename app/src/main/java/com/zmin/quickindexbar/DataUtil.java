package com.zmin.quickindexbar;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据获取来源类
 */
public class DataUtil {
    public static final int MODEL_COUNT = 66;

    public static List<Model> getData() {
        List<Model> stickyExampleModels = new ArrayList<>();

        for (int index = 0; index < MODEL_COUNT; index++) {
            if (index < 1) {
                stickyExampleModels.add(new Model(
                        "A", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 3) {
                stickyExampleModels.add(new Model(
                        "B", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 4) {
                stickyExampleModels.add(new Model(
                        "C", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 7) {
                stickyExampleModels.add(new Model(
                        "D", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 9) {
                stickyExampleModels.add(new Model(
                        "E", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 11) {
                stickyExampleModels.add(new Model(
                        "F", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 13) {
                stickyExampleModels.add(new Model(
                        "G", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 16) {
                stickyExampleModels.add(new Model(
                        "H", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 17) {
                stickyExampleModels.add(new Model(
                        "I", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 20) {
                stickyExampleModels.add(new Model(
                        "J", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 23) {
                stickyExampleModels.add(new Model(
                        "K", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 28) {
                stickyExampleModels.add(new Model(
                        "L", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 30) {
                stickyExampleModels.add(new Model(
                        "M", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 37) {
                stickyExampleModels.add(new Model(
                        "N", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 39) {
                stickyExampleModels.add(new Model(
                        "O", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 40) {
                stickyExampleModels.add(new Model(
                        "P", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 42) {
                stickyExampleModels.add(new Model(
                        "Q", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 43) {
                stickyExampleModels.add(new Model(
                        "R", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 50) {
                stickyExampleModels.add(new Model(
                        "S", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 51) {
                stickyExampleModels.add(new Model(
                        "T", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 52) {
                stickyExampleModels.add(new Model(
                        "U", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 53) {
                stickyExampleModels.add(new Model(
                        "V", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 54) {
                stickyExampleModels.add(new Model(
                        "W", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 59) {
                stickyExampleModels.add(new Model(
                        "X", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 60) {
                stickyExampleModels.add(new Model(
                        "Y", "name" + index, "gender" + index, "profession" + index));
            } else  {
                stickyExampleModels.add(new Model(
                        "Z", "name" + index, "gender" + index, "profession" + index));
            }
        }

        return stickyExampleModels;
    }
}
