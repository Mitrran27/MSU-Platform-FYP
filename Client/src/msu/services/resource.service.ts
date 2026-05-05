import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment.development";
import {HttpClient} from "@angular/common/http";
import {ResourceType} from "../../types/resource.type";

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  API = environment.API

  constructor(private http: HttpClient) {
  }

  uploadFile(form: FormData) {
    return this.http.post(this.API + '/uploadFile', form)
  }

  addResource(data: ResourceType) {
    return this.http.post(this.API + '/resource', data)
  }

  getAllResources(){
    return this.http.get(this.API + '/resource')
  }
}
