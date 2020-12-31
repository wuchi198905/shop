package com.example.shop.pub.bean;

import lombok.Data;

import java.util.List;

@Data
public class DynamicDTO extends Dynamic{
    List<Dynamic> list;
}
