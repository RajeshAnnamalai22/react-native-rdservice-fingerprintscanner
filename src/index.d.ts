declare module "react-native-rdservice-fingerprintscanner" {
    export type deviceInfoProps = {
        isWhitelisted: boolean,
        rdServiceInfoJson: JSON,
        rdServiceInfoXML: string,
        rdServicePackage: string ,
        status: number,
        message: string,
    }
    
    export type optionalInfo ={
        status: number,
        message: string,
    }
  
   export type fingerprintDataProps = {
      pidDataJson: JSON,
      pidDataXML: string,
      rdServicePackage: string,
      status: number,
      errInfo: string,
      errorCode: number,
    }

    export function getDeviceInfo(): Promise<deviceInfoProps | optionalInfo>;
    export function captureFinger(pidOptions: string):Promise<fingerprintDataProps>;

}