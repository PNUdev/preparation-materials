package org.pnu;

public interface MaskService {
    String MASK_CHARACTER = "*";

    String algorithm();
    String maskValue(String inputValue, Integer nonMaskedLength);
}
