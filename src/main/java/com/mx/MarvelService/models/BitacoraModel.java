package com.mx.marvelservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "peticiones")
public class BitacoraModel {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="idusuario")
	 private long idusuario;
	 
	 @Column(name="fecha")
	 private String fecha;
	 
	 @Column(name="api")
	 private String api;
	 
	 @Column(name="request")
	 private String request;
	 
	 @Column(name="response")
	 private String response; 
     
}
