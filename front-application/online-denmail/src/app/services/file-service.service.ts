import { Injectable } from '@angular/core';
import * as AWS from 'aws-sdk';
import { GetObjectOutput } from 'aws-sdk/clients/s3';
import { PromiseResult } from 'aws-sdk/lib/request';

@Injectable({
  providedIn: 'root',
})
export class FileServiceService {
  s3: AWS.S3;

  constructor() {
    AWS.config.region = 'ap-northeast-1';
    AWS.config.credentials = new AWS.CognitoIdentityCredentials({
      IdentityPoolId: 'ap-northeast-1:acd43dc3-64e3-40a4-ba92-e7335a54edf4',
    });
    this.s3 = new AWS.S3();
  }

  getS3FileList(kanriNo: string): Promise<PromiseResult<AWS.S3.ListObjectsV2Output, AWS.AWSError>> {
    const params: AWS.S3.Types.ListObjectsV2Request = {
      Bucket: 'denmail-test',
      Prefix: kanriNo + '/',
    };

    return this.s3.listObjectsV2(params).promise();
  }

  getS3File(fileKey: string): Promise<PromiseResult<GetObjectOutput, AWS.AWSError>> {
    const params: AWS.S3.Types.GetObjectRequest = {
      Bucket: 'denmail-test',
      Key: fileKey,
    };
    return this.s3.getObject(params).promise();
  }
}
