import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Modal} from "bootstrap";
import {ResourceType} from "../../types/resource.type";
import {ResourceService} from "../services/resource.service";

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.sass']
})
export class ResourcesComponent implements AfterViewInit, OnInit {

  resources: ResourceType[] = []
  loading: boolean = false;

  resource: ResourceType = {
    description: "", fileName: "", filePath: "", fileSize: "", fileType: "", title: "", userId: 0
  }
  addModal!: Modal

  constructor(private rs: ResourceService) {
  }

  ngOnInit() {
    this.getAllResources()
  }

  ngAfterViewInit() {
    this.addModal = new Modal(document.getElementById('addModal') as HTMLElement)

  }

  getInputValue(target: EventTarget | null) {
    return (target as HTMLInputElement).value
  }

  showAddModal() {
    this.addModal.show()
  }

  addNewResource() {
    this.resource.userId = Number(localStorage.getItem('userId'))
    this.rs.addResource(this.resource).subscribe(r => {
      console.log(r)
      this.addModal.hide()
      this.getAllResources()
    })
  }

  getAllResources(){
    this.rs.getAllResources().subscribe((r:any)=>{
      console.log(r)
      this.resources = r.data
    })
  }

  onFileSelected(event: Event) {
    let files = (event.target as HTMLInputElement).files
    console.log(files?.item(0))

    let data = new FormData();
    data.append("file", files?.item(0) as any);
    this.rs.uploadFile(data).subscribe((r: any) => {
      console.log(r)
      this.resource.fileName = r.fileName
      this.resource.fileSize = r.fileSize
      this.resource.filePath = r.url
    })
  }
}
