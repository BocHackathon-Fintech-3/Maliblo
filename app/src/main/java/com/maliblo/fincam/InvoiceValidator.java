package com.maliblo.fincam;

import android.util.Log;

public class InvoiceValidator {

    private static String _company;
    private static String _barcode;
    private static String _invoice_no;
    private static String _invoice_check_digits;
    private static String _total;

    private static String errorMsg="Error";

    public static Boolean validateEAC(String company_name,String barcode,String invoice_no,String invoice_check_digits,String total){
        //validate company_name
        if (company_name.contains(Constants.EAC)){
            _company = Constants.EAC;
        }else{
            if(company_name.contains("Electricity") || company_name.contains("Authority") ){
                _company = Constants.EAC;
            }else{
                //TODO implement Levenshtein Distance
                //unable to detect company
                errorMsg = "Company Detection Failed";
                return false;
            }
        }
//        barcode = replaceSimilarities(barcode);
//        invoice_no = replaceSimilarities(invoice_no);
//        invoice_check_digits = replaceSimilarities(invoice_check_digits);
        _total = total.substring(0, total.length()-2) + "," + total.substring(total.length()-2);
        _invoice_no=invoice_no;
        _barcode=barcode;
        _invoice_check_digits=invoice_check_digits;

        if(barcode.length()>=Constants.EAC_BARCODE_MIN_LENGTH){
            if(barcode.contains(total) && barcode.contains(_invoice_check_digits) && _invoice_no.length()!=Constants.EAC_INVOICE_NO_LENGTH){
                    if(_invoice_no.contains(barcode.substring(barcode.indexOf(_invoice_check_digits)-Constants.EAC_INVOICE_NO_LENGTH))){
                        Log.d(Constants.LOG_TAG,"Special occation");
                        _invoice_no = barcode.substring(barcode.indexOf(_invoice_check_digits)-Constants.EAC_INVOICE_NO_LENGTH,Constants.EAC_INVOICE_NO_LENGTH);
                    }
            }
            if(barcode.contains(total) && barcode.contains(_invoice_no) && _invoice_check_digits.length()!=Constants.EAC_INVOICE_CHECK_DIGITS_LENGTH){
                _invoice_check_digits = barcode.substring(11,14);
            }
            if(barcode.contains(_invoice_no) && barcode.contains(_invoice_check_digits) && _total.length()<=4){
                total = barcode.substring(_invoice_check_digits.length()+_invoice_no.length()-1);
            }
        }else{
            if(_invoice_no.length()==Constants.EAC_INVOICE_NO_LENGTH && _invoice_check_digits.length()==Constants.EAC_INVOICE_CHECK_DIGITS_LENGTH) {
                _barcode=_invoice_no+_invoice_check_digits+_total;
            }else{
                return false;
            }
        }


        Log.d(Constants.LOG_TAG,"Total:" + _total);
        Log.d(Constants.LOG_TAG,"Invoice No:" + _invoice_no);
        Log.d(Constants.LOG_TAG,"Barcode:" + _barcode);
        Log.d(Constants.LOG_TAG,"Digits:" + _invoice_check_digits);

        Log.d(Constants.LOG_TAG, "BARCODE: " + String.valueOf(barcode.indexOf(invoice_check_digits)));

/*        if (barcode.indexOf(invoice_check_digits)==-1) {
            //barcode or check digits incorrect
            errorMsg = "Barcode and Check Digits don't match";
            return false;
        }

        if(barcode.indexOf(newTotal)!=barcode.length()-newTotal.length()){
            //barcode or total incorrect
            errorMsg = "Barcode and Total don't match";
            return false;
        }*/


        if (!barcode.contains(Constants.EAC_BARCODE_REQUESTED_CHAR ) && barcode.length() > Constants.EAC_BARCODE_MIN_LENGTH){
            _barcode = barcode.substring(0, barcode.length()-2) + "," + barcode.substring(barcode.length()-2, barcode.length());
        }

        return true;
    }

    private static String replaceSimilarities(String str){
        //replace similar to zeroes
        str=str.replace("o","0").replace("O","0");
        //replace similar to ones
        str=str.replace("i","1").replace("l","1");
        return str;
    }

    public static String getErrorMsg() {
        return errorMsg;
    }

    public static String getCompany() {
        return _company;
    }

    public static String getBarcode() {
        return _barcode;
    }

    public static String getInvoice_no() {
        return _invoice_no;
    }

    public static String getInvoice_check_digits() {
        return _invoice_check_digits;
    }

    public static String getTotal() {
        return _total;
    }
}
