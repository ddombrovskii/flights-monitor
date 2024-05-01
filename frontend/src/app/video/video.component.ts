import {Component, OnInit} from '@angular/core';
import {VideoService} from "../service/video.service";
import {ActivatedRoute} from "@angular/router";
import {Video} from "../entity/video";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-video',
  templateUrl: './video.component.html',
  styleUrl: './video.component.css'
})
export class VideoComponent implements OnInit {
  video: Video = {} as Video;
  constructor(private route: ActivatedRoute,
              private videoService: VideoService,
              private sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    this.getVideo();
  }

  getVideo() {
    const flightId: number = parseInt(this.route.snapshot.paramMap.get('flightId')!, 10);
    this.videoService.readVideo(flightId).subscribe(res => this.video = res);
  }
  videoUrl(url: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

}
