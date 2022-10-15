package com.cjcode.projectMinTic.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.PropertyUtils;
import java.beans.PropertyDescriptor;

@Service
@RequiredArgsConstructor
public class UtilsService {
    public Object validateData(Object dataDb, Object dataBody){
        PropertyDescriptor[] objDescriptors = PropertyUtils.getPropertyDescriptors(dataBody);
        for (PropertyDescriptor objDescriptor : objDescriptors) {
            try {
                String propertyName = objDescriptor.getName();
                Object propertyValue = PropertyUtils.getProperty(dataBody, propertyName);
                if(propertyValue != null && !propertyName.equals("id")){
                    PropertyUtils.setProperty(dataDb,propertyName,propertyValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataDb;
    }
}
