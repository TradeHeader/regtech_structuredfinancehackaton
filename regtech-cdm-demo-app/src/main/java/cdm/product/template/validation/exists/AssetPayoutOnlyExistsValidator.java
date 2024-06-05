package cdm.product.template.validation.exists;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.AssetLeg;
import cdm.product.template.AssetPayout;
import cdm.product.template.DividendTerms;
import cdm.product.template.Duration;
import cdm.product.template.Product;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class AssetPayoutOnlyExistsValidator implements ValidatorWithArg<AssetPayout, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AssetPayout> ValidationResult<AssetPayout> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payerReceiver", ExistenceChecker.isSet((PayerReceiver) o.getPayerReceiver()))
				.put("priceQuantity", ExistenceChecker.isSet((ResolvablePriceQuantity) o.getPriceQuantity()))
				.put("principalPayment", ExistenceChecker.isSet((PrincipalPayments) o.getPrincipalPayment()))
				.put("settlementTerms", ExistenceChecker.isSet((SettlementTerms) o.getSettlementTerms()))
				.put("assetLeg", ExistenceChecker.isSet((List<? extends AssetLeg>) o.getAssetLeg()))
				.put("securityInformation", ExistenceChecker.isSet((Product) o.getSecurityInformation()))
				.put("durationType", ExistenceChecker.isSet((Duration) o.getDurationType()))
				.put("minimumFee", ExistenceChecker.isSet((Money) o.getMinimumFee()))
				.put("dividendTerms", ExistenceChecker.isSet((DividendTerms) o.getDividendTerms()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AssetPayout", ValidationType.ONLY_EXISTS, "AssetPayout", path, "");
		}
		return failure("AssetPayout", ValidationType.ONLY_EXISTS, "AssetPayout", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
