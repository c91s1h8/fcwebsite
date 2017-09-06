package com.feelcolor.website.java8;

import com.feelcolor.website.model.po.UserInfo;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Case {
    static List<Integer> integerList = Arrays.asList(5, 2, 4, 3, 1, 6, 7, 1, 3, 5);
    static List<String> stringList = Arrays.asList("a", "asds", ".net", "java", "hello java", "javalambda", "ccc");
    static List<UserInfo> userList = new ArrayList<UserInfo>() {
        {
            add(new UserInfo("1", "Mahesh", 17));
            add(new UserInfo("2", "Suresh", 13));
            add(new UserInfo("3", "Nilesh", 15));
            add(new UserInfo("4", "csh", 15));
        }
    };

    public static void main(String[] args) {
        // streamConcat(integerList.stream(),integerList.stream()).forEach(System.out::println);
        // System.out.println(findFristGreaterThan(integerList, 6));
        // System.out.println(getGreaterThanList(integerList, 2));
        // System.out.println(getContainsStrList(stringList,"java"));
        // valueAdd(integerList, 10).forEach(System.out::println);
        // System.out.println(sumByReduce(integerList));
        // System.out.println(sum(userList));
        // System.out.println(statistics(userList).getAverage());
        // filterByMethod(userList).forEach(System.out::println);
        // System.out.println(groupByAge(userList));
        // System.out.println(partitionByAge(userList, 15));
        // System.out.println(getMaxByAge(userList));
        // sumByReduce(integerList);
        // userList.stream().map(UserInfo::getName).collect(Collectors.toList()).forEach(System.out::println);
        // paralleMap(userList).forEach(System.out::println);
        // paralleFilter(userList,14).forEach(System.out::println);
        // forEach(integerList);
//        getUser(new UserInfo());
        System.out.println(orElseGet(new UserInfo()));
    }

    /**
     * 合并两个Stream
     * 
     * @param stream1
     * @param stream2
     * @return
     */
    public static Stream<?> streamConcat(Stream<?> stream1, Stream<?> stream2) {
        return Stream.concat(stream1, stream2);
    }

    /**
     * 返回list中第一个大于num的数
     * 
     * @param list
     * @param num
     * @return
     */
    public static Integer findFristGreaterThan(List<Integer> list, Integer num) {
        return list.stream().filter(n -> (n > num)).findFirst().orElse(0);
    }

    /**
     * 返回list中大于num的所有数字
     * 
     * @param list
     * @param num
     * @return
     */
    public static List<Integer> getGreaterThanList(List<Integer> list, Integer num) {
        return list.stream().filter(n -> (n > num)).collect(Collectors.toList());
    }

    /**
     * 获取list中包含str的字符
     * 
     * @param list
     * @param str
     * @return
     */
    public static List<String> getContainsStrList(List<String> list, String str) {
        return list.stream().filter(n -> n.contains(str)).collect(Collectors.toList());
    }

    /**
     * list所有值加num
     * 
     * @param list
     * @param num
     * @return
     */
    public static List<Integer> valueAdd(List<Integer> list, Integer num) {
        return list.stream().map(n -> n + num).collect(Collectors.toList());
    }

    /**
     * reduce计算求和
     * 
     * @param list
     * @return
     */
    public static Integer sumByReduce(List<Integer> list) {
        return list.stream().reduce((a, b) -> {
            System.out.println("我是上次执行结果：" + a);
            System.out.println("我是stream中元素：" + b);
            return a + b;
        }).orElse(0);
    }

    /**
     * 求和
     * 
     * @param list
     * @return
     */
    public static Integer sumByMapToInt(List<UserInfo> list) {
        return list.stream().mapToInt(user -> user.getAge()).sum();
    }

    /**
     * 统计
     * 
     * @param list
     * @return
     */
    public static DoubleSummaryStatistics statistics(List<UserInfo> list) {
        return list.stream().mapToDouble(user -> user.getAge()).summaryStatistics();
    }

    /**
     * 跳过num个
     * 
     * @param list
     * @param num
     * @return
     */
    public static List<Integer> skip(List<Integer> list, Long num) {
        return list.stream().skip(num).collect(Collectors.toList());
    }

    /**
     * 取limit条数据
     * 
     * @param list
     * @param num
     * @return
     */
    public static List<Integer> limit(List<Integer> list, Long num) {
        return list.stream().limit(num).collect(Collectors.toList());
    }

    /**
     * 去重
     * 
     * @param list
     * @return
     */
    public static List<?> distinct(List<?> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    /**
     * list是否全部大于num
     * 
     * @param list
     * @param num
     * @return
     */
    public static boolean allMatch(List<Integer> list, Integer num) {
        return list.stream().allMatch(n -> n > num);
    }

    /**
     * list是否有大于num的
     * 
     * @param list
     * @param num
     * @return
     */
    public static boolean anyMatch(List<Integer> list, Integer num) {
        return list.stream().anyMatch(n -> n > num);
    }

    /**
     * 利用方法进行filter过滤
     * 
     * @param list
     * @return
     */
    public static List<UserInfo> filterByMethod(List<UserInfo> list) {
        return list.stream().filter(n -> isBetween10And15(n)).collect(Collectors.toList());
    }

    public static boolean isBetween10And15(UserInfo user) {
        return user.getAge() > 10 && user.getAge() < 15;
    }

    /**
     * 根据Age分组
     * 
     * @param list
     * @return
     */
    public static Map<Integer, List<UserInfo>> groupByAge(List<UserInfo> list) {
        return list.stream().collect(Collectors.groupingBy(UserInfo::getAge));
    }

    /**
     * 根据条件进行分区
     * 
     * @param list
     * @param num
     * @return
     */
    public static Map<Boolean, List<UserInfo>> partitionByAge(List<UserInfo> list, Integer num) {
        return list.stream().collect(Collectors.partitioningBy(n -> n.getAge() > num));
    }

    /**
     * 获取age最小的对象
     * 
     * @param list
     * @return
     */
    public static UserInfo getMinByAge(List<UserInfo> list) {
        return list.stream().min(new Comparator<UserInfo>() {
            @Override
            public int compare(UserInfo o1, UserInfo o2) {
                return o1.getAge() - o2.getAge();
            }
        }).orElse(null);
    }

    /**
     * 返回age最大的对象
     * 
     * @param list
     * @return
     */
    public static UserInfo getMaxByAge(List<UserInfo> list) {
        return list.stream().max(new Comparator<UserInfo>() {
            @Override
            public int compare(UserInfo o1, UserInfo o2) {
                return o1.getAge() - o2.getAge();
            }
        }).orElse(null);
    }

    /**
     * 根据comparator排序
     * 
     * @param list
     * @return
     */
    public static List<UserInfo> sort(List<UserInfo> list) {
        return list.stream().sorted(Comparator.comparing(UserInfo::getAge)).collect(Collectors.toList());
    }

    /**
     * 反转排序
     * 
     * @param list
     * @return
     */
    public static List<UserInfo> reversed(List<UserInfo> list) {
        return list.stream().sorted(Comparator.comparing(UserInfo::getAge).reversed()).collect(Collectors.toList());
    }

    /**
     * 并行Stream map Function
     * 
     * @param list
     * @return
     */
    public static List<UserInfo> paralleMap(List<UserInfo> list) {
        return list.parallelStream().map(new Function<UserInfo, UserInfo>() {
            @Override
            public UserInfo apply(UserInfo userInfo) {
                userInfo.setAge(userInfo.getAge() + 10);
                return userInfo;
            }
        }).collect(Collectors.toList());
    }

    /**
     * 并行Stream filter Predicate
     * 
     * @param list
     * @param num
     * @return
     */
    public static List<UserInfo> paralleFilter(List<UserInfo> list, Integer num) {
        return list.parallelStream().filter(new Predicate<UserInfo>() {
            @Override
            public boolean test(UserInfo userInfo) {
                return Integer.compare(userInfo.getAge(), num) > 0;
            }
        }).collect(Collectors.toList());
    }

    public static void forEach(List<?> list) {
        list.stream().forEach(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        });
    }

    /**
     * 如果user不是null 则执行输出
     * 
     * @param user
     */
    public static void ifPresent(UserInfo user) {
        Optional<UserInfo> userInfo = Optional.ofNullable(user);
        userInfo.ifPresent(new Consumer<UserInfo>() {
            @Override
            public void accept(UserInfo userInfo) {
                System.out.println(userInfo);
            }
        });
    }

    /**
     * 如果为null 则利用方法进行创建返回
     * @param userInfo
     * @return
     */
    public static UserInfo orElseGet(UserInfo userInfo) {
        Optional<UserInfo> userInfoOptional = Optional.ofNullable(userInfo);
        return userInfoOptional.orElseGet(new Supplier<UserInfo>() {
            @Override
            public UserInfo get() {
                return new UserInfo("111", "22", 12);
            }
        });
    }

/*    public static Object randomBetween(int start , int end){
        Random random = new Random();
        IntStream intStream = random.ints(start,end);
        List<Integer> list = intStream.limit(1).collect(Collectors.toList());

    }*/

}
