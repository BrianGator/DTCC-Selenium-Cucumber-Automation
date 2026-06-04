import json
from dataclasses import dataclass
from typing import Any


@dataclass(frozen=True)
class ApiContract:
    required_fields: set[str]
    expected_status: int


def validate_response(status_code: int, response_body: str, contract: ApiContract) -> bool:
    payload: dict[str, Any] = json.loads(response_body)
    missing = contract.required_fields.difference(payload.keys())
    if status_code != contract.expected_status:
        raise AssertionError(f"Expected HTTP {contract.expected_status}, received {status_code}")
    if missing:
        raise AssertionError(f"Missing fields: {sorted(missing)}")
    return True


if __name__ == "__main__":
    contract = ApiContract(required_fields={"transactionId", "status", "amount"}, expected_status=200)
    sample = '{"transactionId":"TXN-1001","status":"PROCESSED","amount":1500.25}'
    print(validate_response(200, sample, contract))
