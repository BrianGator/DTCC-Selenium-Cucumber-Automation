type OrderResponse = { orderId: string; transactionRef: string; status: string };
const response: OrderResponse = { orderId: "ORD-1", transactionRef: "TXN-1", status: "PROCESSED" };
console.assert(response.status === "PROCESSED");
