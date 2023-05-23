package com.example.pruebapersonal.servicios;

import com.example.pruebapersonal.entidades.Autor;
import com.example.pruebapersonal.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio implements ServicioBase<Autor>{

    @Autowired
    private AutorRepositorio repositorio;

    @Override
    public List<Autor> buscarTodos() throws Exception {
       try{
           return repositorio.findAll();
       }
       catch (Exception error){
           throw new Exception(error.getMessage());
       }
    }

    @Override
    public Autor buscarId(Integer id) throws Exception {
        try{
            Autor autor= repositorio.findById(id).orElse(null);
            if(autor==null){
                throw new Exception("El autor no existe");
            }
            return autor;
        }
        catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Autor registrar(Autor datosEntidad) throws Exception {
        try{
            repositorio.save(datosEntidad);
            return datosEntidad;
        }
        catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Autor actualizar(Integer id, Autor datosEntidadNuevos) throws Exception {
        try{
            Optional<Autor> autor =repositorio.findById(id);
            if(autor.isEmpty()){
                throw new Exception("El autor no existe");
            }

            Autor existente =autor.get();
            existente.setNombre(datosEntidadNuevos.getNombre());
            Autor actualizado=repositorio.save(existente);
            return actualizado;

        } catch(Exception error){
            throw new Exception(error.getMessage());
        }

    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        try{
            Optional<Autor> autor =repositorio.findById(id);
            if(autor.isEmpty()){
                throw new Exception("El autor no existe");
            }
            repositorio.deleteById(id);
            return true;

        }catch(Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
