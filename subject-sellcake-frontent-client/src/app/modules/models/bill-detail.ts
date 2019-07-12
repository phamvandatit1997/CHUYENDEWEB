import {Product} from './product';
import {Bills} from './bills';
import {ProductTypes} from './product-types';

export class BillDetail {
  bill: Bills;
  product: Product;
  productTypes: ProductTypes;
  quantity: number;
  unitPrice: number;
  sumPrice: number;
}
