package com.east.demo.model.dto.base.resp;

import lombok.Data;

import java.util.List;

/**
 * 分页响应信息
 *
 * @author: east
 * @date: 2023/11/16
 */
@Data
public class BasePageInfoResp<S extends CommonSummary, T> {
    /**
     * 这里用extends CommonSummary限定原因主要是：
     * 保证响应的概略信息有统一的格式
     */
    protected S summary;
    protected List<T> detail;

    /**
     * 自定义的概略信息,可以定义为内部类，也可以在外部继承CommonSummary后使用
     * 放这里两个目的：
     * 1. 方便使用，不建新类了
     * 2. 测试内部类在resultMap中使用时顺序问题
     * todo 因为没有设置默认构造函数造成mybatis处理返回结果时无法找到map的对应关系。奇怪？为什么之前生成的是全参构造，而这里是无参？
     *
     *
     如果你发现在一个类上使用了@RequiredArgsConstructor注解，但即使类中没有final或@NonNull注解的字段，编译后也没有生成一个无参构造函数，可能的原因包括：

     已存在的构造函数：如果你的类中已经定义了任何构造函数（无论是无参还是有参），Lombok将不会自动生成无参构造函数。Lombok只在没有手动定义任何构造函数的情况下生成默认的构造函数。

     Lombok插件或依赖问题：如果IDE或构建工具没有正确配置Lombok插件或依赖，Lombok的注解处理可能不会正常工作。确保你的开发环境（如IntelliJ IDEA、Eclipse等）安装了Lombok插件，并且你的项目中包含了正确版本的Lombok依赖。

     Lombok配置：在某些情况下，Lombok的行为可能会受到lombok.config文件中的配置设置的影响。检查项目中是否存在这样的配置文件，并确认是否有任何配置项可能影响构造函数的生成。

     注解处理器未启用：在某些构建工具或IDE中，可能需要明确启用注解处理器。例如，在IntelliJ IDEA中，需要在“设置”或“首选项”中启用“注解处理器”。

     IDE缓存问题：有时IDE的缓存问题可能导致Lombok的行为看起来不一致。尝试清除IDE缓存并重新加载项目可能有助于解决这个问题。

     Lombok版本：虽然不太可能，但不同版本的Lombok可能会有不同的行为。检查你正在使用的Lombok版本，并参考相关文档或更新日志了解具体行为。

     为了排查问题，你可以尝试以下步骤：

     确认项目中没有手动定义的构造函数。
     清除并重新构建项目，确保使用的是最新的编译结果。
     检查IDE和构建工具中的Lombok配置。
     如果可能，尝试使用不同版本的Lombok。
     *
     */
    @Data
    public static class MyCommonSummary extends CommonSummary {
        private String total;
    }
}
