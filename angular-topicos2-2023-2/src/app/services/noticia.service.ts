import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Noticia } from '../models/noticia.model';

@Injectable({
  providedIn: 'root'
})
export class NoticiaService {

  private baseURL: string =  'http://localhost:8080';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Noticia[]> {
    return this.http.get<Noticia[]>(`${this.baseURL}/noticias`);
  }

  findById(id: string): Observable<Noticia> {
    return this.http.get<Noticia>(`${this.baseURL}/noticias/${id}`);
  }

  save(noticia: Noticia): Observable<Noticia> {
    return this.http.post<Noticia>(`${this.baseURL}/noticias`, noticia);
  }

  update(noticia: Noticia): Observable<Noticia> {
    return this.http.put<Noticia>(`${this.baseURL}/noticias/${noticia.id}`, noticia);
  }

  delete(noticia: Noticia): Observable<any> {
    return this.http.delete<any>(`${this.baseURL}/noticias/${noticia.id}`);
  }
}
