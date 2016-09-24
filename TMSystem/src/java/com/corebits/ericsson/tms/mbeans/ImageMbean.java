/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.mbeans;

import java.io.Serializable;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Tommy
 */
@Named(value = "image")
@ApplicationScoped
public class ImageMbean  implements Serializable{

    private byte[] photo;

    public ImageMbean() {
    }

    public byte[] userPhoto() {
        try {
            Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            byte[] sphoto = (byte[]) params.get("photo");
            photo = sphoto;
//            System.out.println("User Photo : " + Arrays.toString(photo));
        } catch (Exception e) {
            System.out.println("Exception getting user photo from session : " + e.getMessage());
        }
        return photo;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }   
    
}
