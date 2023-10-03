import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Genero } from '../models/genero.model';

@Injectable({
  providedIn: 'root'
})
export class GeneroService {

  private baseURL: string =  'http://localhost:8080';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Genero[]> {
    return this.http.get<Genero[]>(`${this.baseURL}/generos`);
  }

  findById(id: string): Observable<Genero> {
    return this.http.get<Genero>(`${this.baseURL}/generos/${id}`);
  }

  save(genero: Genero): Observable<Genero> {
    return this.http.post<Genero>(`${this.baseURL}/generos`, genero);
  }

  update(genero: Genero): Observable<Genero> {
    return this.http.put<Genero>(`${this.baseURL}/generos/${genero.id}`, genero);
  }

  delete(genero: Genero): Observable<any> {
    return this.http.delete<any>(`${this.baseURL}/generos/${genero.id}`);
  }
}
