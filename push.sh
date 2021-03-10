#!/bin/bash

read -p "输入备注：🎨 " message
if [ -z $message ]
then
  message="规范 "
fi

result=`git branch | grep "*"` # 获取分支名
curBranch=${result:2} # 去除多余的*

git add .
git commit -m "🎨 $message"

git push origin $curBranch # 提交代码到github(修改了远程项目名)

echo "🎨 $message"