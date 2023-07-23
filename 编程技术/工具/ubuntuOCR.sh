# !/bin/env bash
# Dependencies: tesseract-ocr imagemagick gnome-screenshot xclip

# you can only scan one character at a time
SCR="/home/lsl/data/learn/learnProject/ocr"

# take a shot what you wana to OCR to text
gnome-screenshot -a -f $SCR.png

# increase the png
mogrify -modulate 100,0 -resize 400% $SCR.png
# should increase detection rate

# OCR by tesseract
tesseract $SCR.png $SCR &> /dev/null -l eng+chi_sim

# se sed to delete the blanks & get the text and copy to clipboard
# cat $SCR.txt | sed 's/ //g' | xclip -selection clipboard
# 需要删除换行请使用此语句 并注释上一句
cat $SCR.txt | sed 's/ //g'| xargs | xclip -selection clipboard

# 弹出提示
# notify-send "OCR Done"

exit
