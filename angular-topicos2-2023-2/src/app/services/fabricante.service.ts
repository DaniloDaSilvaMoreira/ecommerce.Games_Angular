import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fabricante } from '../models/fabricante.model';

@Injectable({
  providedIn: 'root'
})
export class FabricanteService {

  private baseURL: string =  'http://localhost:8080';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Fabricante[]> {
    return this.http.get<Fabricante[]>(`${this.baseURL}/fabricantes`);
  }

  findById(id: string): Observable<Fabricante> {
    return this.http.get<Fabricante>(`${this.baseURL}/fabricantes/${id}`);
  }

  save(fabricante: Fabricante): Observable<Fabricante> {
    return this.http.post<Fabricante>(`${this.baseURL}/fabricantes`, fabricante);
  }

  update(fabricante: Fabricante): Observable<Fabricante> {
    return this.http.put<Fabricante>(`${this.baseURL}/fabricantes/${fabricante.id}`, fabricante);
  }

  delete(fabricante: Fabricante): Observable<any> {
    return this.http.delete<any>(`${this.baseURL}/fabricantes/${fabricante.id}`);
  }
}
