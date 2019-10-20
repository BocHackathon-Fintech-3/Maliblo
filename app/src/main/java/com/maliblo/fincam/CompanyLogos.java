package com.maliblo.fincam;

public class CompanyLogos {
    static int setLogo(String companyName) {
        if (companyName.equals(Constants.EPIC)) {
            return R.drawable.epic;
        } else if (companyName.equals(Constants.EAC)) {
            return R.drawable.eac;
        }else if (companyName.equals(Constants.CYTA)){
            return R.drawable.cyta;
        } else return R.drawable.ic_launcher_background;

    }
}
