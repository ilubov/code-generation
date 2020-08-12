package com.i.lubov.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class GenConfigDTO implements Serializable {
    @ApiModelProperty("ID")
    @NotNull(groups = {GenConfigDTO.Update.class}, message = "ID不能为空")
    private Long id;
    @ApiModelProperty("作者名称")
    private String author;
    @ApiModelProperty("模块名称")
    private String moduleName;
    @ApiModelProperty("至于包下")
    private String pack;
    @ApiModelProperty("服务名称")
    private String serviceName;
    @ApiModelProperty("前端路径")
    private String path;
    @ApiModelProperty("去表前缀")
    private String prefix;

    public GenConfigDTO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public String getPack() {
        return this.pack;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String getPath() {
        return this.path;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public void setModuleName(final String moduleName) {
        this.moduleName = moduleName;
    }

    public void setPack(final String pack) {
        this.pack = pack;
    }

    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof GenConfigDTO)) {
            return false;
        } else {
            GenConfigDTO other = (GenConfigDTO) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label95:
                {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label95;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label95;
                    }

                    return false;
                }

                Object this$author = this.getAuthor();
                Object other$author = other.getAuthor();
                if (this$author == null) {
                    if (other$author != null) {
                        return false;
                    }
                } else if (!this$author.equals(other$author)) {
                    return false;
                }

                Object this$moduleName = this.getModuleName();
                Object other$moduleName = other.getModuleName();
                if (this$moduleName == null) {
                    if (other$moduleName != null) {
                        return false;
                    }
                } else if (!this$moduleName.equals(other$moduleName)) {
                    return false;
                }

                label74:
                {
                    Object this$pack = this.getPack();
                    Object other$pack = other.getPack();
                    if (this$pack == null) {
                        if (other$pack == null) {
                            break label74;
                        }
                    } else if (this$pack.equals(other$pack)) {
                        break label74;
                    }

                    return false;
                }

                label67:
                {
                    Object this$serviceName = this.getServiceName();
                    Object other$serviceName = other.getServiceName();
                    if (this$serviceName == null) {
                        if (other$serviceName == null) {
                            break label67;
                        }
                    } else if (this$serviceName.equals(other$serviceName)) {
                        break label67;
                    }

                    return false;
                }

                Object this$path = this.getPath();
                Object other$path = other.getPath();
                if (this$path == null) {
                    if (other$path != null) {
                        return false;
                    }
                } else if (!this$path.equals(other$path)) {
                    return false;
                }

                Object this$prefix = this.getPrefix();
                Object other$prefix = other.getPrefix();
                if (this$prefix == null) {
                    if (other$prefix != null) {
                        return false;
                    }
                } else if (!this$prefix.equals(other$prefix)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GenConfigDTO;
    }

    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $author = this.getAuthor();
        result = result * 59 + ($author == null ? 43 : $author.hashCode());
        Object $moduleName = this.getModuleName();
        result = result * 59 + ($moduleName == null ? 43 : $moduleName.hashCode());
        Object $pack = this.getPack();
        result = result * 59 + ($pack == null ? 43 : $pack.hashCode());
        Object $serviceName = this.getServiceName();
        result = result * 59 + ($serviceName == null ? 43 : $serviceName.hashCode());
        Object $path = this.getPath();
        result = result * 59 + ($path == null ? 43 : $path.hashCode());
        Object $prefix = this.getPrefix();
        result = result * 59 + ($prefix == null ? 43 : $prefix.hashCode());
        return result;
    }

    public String toString() {
        return "GenConfigDTO(id=" + this.getId() + ", author=" + this.getAuthor() + ", moduleName=" + this.getModuleName() + ", pack=" + this.getPack() + ", serviceName=" + this.getServiceName() + ", path=" + this.getPath() + ", prefix=" + this.getPrefix() + ")";
    }

    public @interface Update {
    }
}
