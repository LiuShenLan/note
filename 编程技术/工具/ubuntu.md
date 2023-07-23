# 解压缩

## .tar
* 解压: `tar -xvf FileName.tar`
* 压缩: `tar -cvf FileName.tar DirName`

## .gz
* 解压: `gunzip FileName.gz` `gzip -d FileName.gz`
* 压缩(只能压缩文件): `gzip FileName`

## .tar.gz、.tgz
* 解压: `tar -zxvf FileName.tar.gz`
* 解压(到目标路径): `tar -C DesDirName -zxvf FileName.tar.gz`
* 压缩: `tar -zcvf FileName.tar.gz DirName`

## .zip
* 解压: `unzip FileName.zip`
* 压缩: `zip FileName.zip DirName`
  * -r: 递归处理，将指定目录下的所有文件和子目录一并压缩

## .rar
* 解压: `unrar x FileName.rar`
* 压缩: `rar a FileName.rar DirName`


# 内核相关

[切换ubuntu内核](https://blog.csdn.net/lingdukafeibj/article/details/121209254)

[切换内核前注意安装网卡驱动](https://blog.csdn.net/lyn631579741/article/details/121111960)

# 安装CUDA

[安装CUDA](https://blog.csdn.net/CC977/article/details/122789394)

[安装cuDNN](https://blog.csdn.net/ngy321/article/details/79872207)

[查看cuDNN版本](https://blog.csdn.net/qq_41726670/article/details/124764498)

[Tensorflow与CUDA、cuDNN版本对应](https://tensorflow.google.cn/install/source_windows?hl=en#gpu)