import { TestBed } from '@angular/core/testing';

import { UpdatePointsService } from './update-points.service';

describe('UpdatePointsService', () => {
  let service: UpdatePointsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UpdatePointsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
