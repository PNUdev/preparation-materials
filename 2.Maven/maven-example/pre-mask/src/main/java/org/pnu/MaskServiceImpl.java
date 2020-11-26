package org.pnu;

import org.apache.commons.lang3.StringUtils;

public class MaskServiceImpl implements MaskService {

    @Override
    public String algorithm() {
        return "pre-mask";
    }


    // "",  " ", "  ", null
    @Override
    public String maskValue(String inputValue, Integer nonMaskedLength) {
        if(StringUtils.isBlank(inputValue) || StringUtils.length(inputValue) < (nonMaskedLength+1)){
            return inputValue;
        }
        return StringUtils.leftPad(StringUtils.right(inputValue, nonMaskedLength), inputValue.length(),MASK_CHARACTER);
    }
}
