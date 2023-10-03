import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cidade } from '../models/cidade.model';

@Injectable({
  providedIn: 'root'
})
export class CidadeService {

  private baseURL: string =  'http://localhost:8080';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Cidade[]> {
    return this.http.get<Cidade[]>(`${this.baseURL}/cidades`);
  }

  findById(id: string): Observable<Cidade> {
    return this.http.get<Cidade>(`${this.baseURL}/cidades/${id}`);
  }

  save(cidade: Cidade): Observable<Cidade> {
    return this.http.post<Cidade>(`${this.baseURL}/cidades`, cidade);
  }

  update(cidade: Cidade): Observable<Cidade> {
    return this.http.put<Cidade>(`${this.baseURL}/cidades/${cidade.id}`, cidade);
  }

  delete(cidade: Cidade): Observable<any> {
    return this.http.delete<any>(`${this.baseURL}/cidades/${cidade.id}`);
  }
}
