package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.CollateralTaxonomyValue;
import cdm.base.staticdata.asset.common.CollateralTaxonomyValue.CollateralTaxonomyValueBuilder;
import cdm.base.staticdata.asset.common.CollateralTaxonomyValue.CollateralTaxonomyValueBuilderImpl;
import cdm.base.staticdata.asset.common.CollateralTaxonomyValue.CollateralTaxonomyValueImpl;
import cdm.base.staticdata.asset.common.EU_EMIR_EligibleCollateralEnum;
import cdm.base.staticdata.asset.common.UK_EMIR_EligibleCollateralEnum;
import cdm.base.staticdata.asset.common.US_CFTC_PR_EligibleCollateralEnum;
import cdm.base.staticdata.asset.common.meta.CollateralTaxonomyValueMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies the collateral taxonomy value, either as a specified enumeration or as a string.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralTaxonomyValue", builder=CollateralTaxonomyValue.CollateralTaxonomyValueBuilderImpl.class, version="${project.version}")
public interface CollateralTaxonomyValue extends RosettaModelObject {

	CollateralTaxonomyValueMeta metaData = new CollateralTaxonomyValueMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identifies European Union Eligible Collateral Assets classification categories based on EMIR Uncleared Margin Rules. Eligible Collateral asset classes for both initial margin (IM) and variation margin (VM) posted and collected between specified entities. Please note: EMIR regulation will detail which eligible collateral assets classes apply to each type of entity pairing (counterparty) and which apply to posting of IM and VM
	 */
	List<EU_EMIR_EligibleCollateralEnum> getEu_EMIR_EligibleCollateral();
	/**
	 * Identifies United Kingdom Eligible Collateral Assets classification categories based on UK Onshored EMIR Uncleared Margin Rules Eligible Collateral asset classes for both initial margin (IM) and variation margin (VM) posted and collected between specified entities. Please note: UK EMIR regulation will detail which eligible collateral assets classes apply to each type of entity pairing (counterparty) and which apply to posting of IM and VM.
	 */
	List<UK_EMIR_EligibleCollateralEnum> getUk_EMIR_EligibleCollateral();
	/**
	 * Identifies US Eligible Collateral Assets classification categories based on Uncleared Margin Rules published by the CFTC and the US Prudential Regulator. Note: While the same basic categories exist in the CFTC and US Prudential Regulators’ margin rules, the precise definitions or application of those rules could differ between the two rules.
	 */
	List<US_CFTC_PR_EligibleCollateralEnum> getUs_CFTC_PR_EligibleCollateral();
	/**
	 * Identifies the taxonomy value when not specified as an enumeration.
	 */
	List<? extends FieldWithMetaString> getNonEnumeratedTaxonomyValue();

	/*********************** Build Methods  ***********************/
	CollateralTaxonomyValue build();
	
	CollateralTaxonomyValue.CollateralTaxonomyValueBuilder toBuilder();
	
	static CollateralTaxonomyValue.CollateralTaxonomyValueBuilder builder() {
		return new CollateralTaxonomyValue.CollateralTaxonomyValueBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralTaxonomyValue> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralTaxonomyValue> getType() {
		return CollateralTaxonomyValue.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("eu_EMIR_EligibleCollateral"), EU_EMIR_EligibleCollateralEnum.class, getEu_EMIR_EligibleCollateral(), this);
		processor.processBasic(path.newSubPath("uk_EMIR_EligibleCollateral"), UK_EMIR_EligibleCollateralEnum.class, getUk_EMIR_EligibleCollateral(), this);
		processor.processBasic(path.newSubPath("us_CFTC_PR_EligibleCollateral"), US_CFTC_PR_EligibleCollateralEnum.class, getUs_CFTC_PR_EligibleCollateral(), this);
		processRosetta(path.newSubPath("nonEnumeratedTaxonomyValue"), processor, FieldWithMetaString.class, getNonEnumeratedTaxonomyValue());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralTaxonomyValueBuilder extends CollateralTaxonomyValue, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateNonEnumeratedTaxonomyValue(int _index);
		List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getNonEnumeratedTaxonomyValue();
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addEu_EMIR_EligibleCollateral(EU_EMIR_EligibleCollateralEnum eu_EMIR_EligibleCollateral0);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addEu_EMIR_EligibleCollateral(EU_EMIR_EligibleCollateralEnum eu_EMIR_EligibleCollateral1, int _idx);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addEu_EMIR_EligibleCollateral(List<? extends EU_EMIR_EligibleCollateralEnum> eu_EMIR_EligibleCollateral2);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder setEu_EMIR_EligibleCollateral(List<? extends EU_EMIR_EligibleCollateralEnum> eu_EMIR_EligibleCollateral3);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUk_EMIR_EligibleCollateral(UK_EMIR_EligibleCollateralEnum uk_EMIR_EligibleCollateral0);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUk_EMIR_EligibleCollateral(UK_EMIR_EligibleCollateralEnum uk_EMIR_EligibleCollateral1, int _idx);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUk_EMIR_EligibleCollateral(List<? extends UK_EMIR_EligibleCollateralEnum> uk_EMIR_EligibleCollateral2);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder setUk_EMIR_EligibleCollateral(List<? extends UK_EMIR_EligibleCollateralEnum> uk_EMIR_EligibleCollateral3);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUs_CFTC_PR_EligibleCollateral(US_CFTC_PR_EligibleCollateralEnum us_CFTC_PR_EligibleCollateral0);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUs_CFTC_PR_EligibleCollateral(US_CFTC_PR_EligibleCollateralEnum us_CFTC_PR_EligibleCollateral1, int _idx);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUs_CFTC_PR_EligibleCollateral(List<? extends US_CFTC_PR_EligibleCollateralEnum> us_CFTC_PR_EligibleCollateral2);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder setUs_CFTC_PR_EligibleCollateral(List<? extends US_CFTC_PR_EligibleCollateralEnum> us_CFTC_PR_EligibleCollateral3);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValue(FieldWithMetaString nonEnumeratedTaxonomyValue0);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValue(FieldWithMetaString nonEnumeratedTaxonomyValue1, int _idx);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValueValue(String nonEnumeratedTaxonomyValue2);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValueValue(String nonEnumeratedTaxonomyValue3, int _idx);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValue(List<? extends FieldWithMetaString> nonEnumeratedTaxonomyValue4);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder setNonEnumeratedTaxonomyValue(List<? extends FieldWithMetaString> nonEnumeratedTaxonomyValue5);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValueValue(List<? extends String> nonEnumeratedTaxonomyValue6);
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder setNonEnumeratedTaxonomyValueValue(List<? extends String> nonEnumeratedTaxonomyValue7);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("eu_EMIR_EligibleCollateral"), EU_EMIR_EligibleCollateralEnum.class, getEu_EMIR_EligibleCollateral(), this);
			processor.processBasic(path.newSubPath("uk_EMIR_EligibleCollateral"), UK_EMIR_EligibleCollateralEnum.class, getUk_EMIR_EligibleCollateral(), this);
			processor.processBasic(path.newSubPath("us_CFTC_PR_EligibleCollateral"), US_CFTC_PR_EligibleCollateralEnum.class, getUs_CFTC_PR_EligibleCollateral(), this);
			processRosetta(path.newSubPath("nonEnumeratedTaxonomyValue"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getNonEnumeratedTaxonomyValue());
		}
		

		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralTaxonomyValue  ***********************/
	class CollateralTaxonomyValueImpl implements CollateralTaxonomyValue {
		private final List<EU_EMIR_EligibleCollateralEnum> eu_EMIR_EligibleCollateral;
		private final List<UK_EMIR_EligibleCollateralEnum> uk_EMIR_EligibleCollateral;
		private final List<US_CFTC_PR_EligibleCollateralEnum> us_CFTC_PR_EligibleCollateral;
		private final List<? extends FieldWithMetaString> nonEnumeratedTaxonomyValue;
		
		protected CollateralTaxonomyValueImpl(CollateralTaxonomyValue.CollateralTaxonomyValueBuilder builder) {
			this.eu_EMIR_EligibleCollateral = ofNullable(builder.getEu_EMIR_EligibleCollateral()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.uk_EMIR_EligibleCollateral = ofNullable(builder.getUk_EMIR_EligibleCollateral()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.us_CFTC_PR_EligibleCollateral = ofNullable(builder.getUs_CFTC_PR_EligibleCollateral()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.nonEnumeratedTaxonomyValue = ofNullable(builder.getNonEnumeratedTaxonomyValue()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("eu_EMIR_EligibleCollateral")
		public List<EU_EMIR_EligibleCollateralEnum> getEu_EMIR_EligibleCollateral() {
			return eu_EMIR_EligibleCollateral;
		}
		
		@Override
		@RosettaAttribute("uk_EMIR_EligibleCollateral")
		public List<UK_EMIR_EligibleCollateralEnum> getUk_EMIR_EligibleCollateral() {
			return uk_EMIR_EligibleCollateral;
		}
		
		@Override
		@RosettaAttribute("us_CFTC_PR_EligibleCollateral")
		public List<US_CFTC_PR_EligibleCollateralEnum> getUs_CFTC_PR_EligibleCollateral() {
			return us_CFTC_PR_EligibleCollateral;
		}
		
		@Override
		@RosettaAttribute("nonEnumeratedTaxonomyValue")
		public List<? extends FieldWithMetaString> getNonEnumeratedTaxonomyValue() {
			return nonEnumeratedTaxonomyValue;
		}
		
		@Override
		public CollateralTaxonomyValue build() {
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder toBuilder() {
			CollateralTaxonomyValue.CollateralTaxonomyValueBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralTaxonomyValue.CollateralTaxonomyValueBuilder builder) {
			ofNullable(getEu_EMIR_EligibleCollateral()).ifPresent(builder::setEu_EMIR_EligibleCollateral);
			ofNullable(getUk_EMIR_EligibleCollateral()).ifPresent(builder::setUk_EMIR_EligibleCollateral);
			ofNullable(getUs_CFTC_PR_EligibleCollateral()).ifPresent(builder::setUs_CFTC_PR_EligibleCollateral);
			ofNullable(getNonEnumeratedTaxonomyValue()).ifPresent(builder::setNonEnumeratedTaxonomyValue);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralTaxonomyValue _that = getType().cast(o);
		
			if (!ListEquals.listEquals(eu_EMIR_EligibleCollateral, _that.getEu_EMIR_EligibleCollateral())) return false;
			if (!ListEquals.listEquals(uk_EMIR_EligibleCollateral, _that.getUk_EMIR_EligibleCollateral())) return false;
			if (!ListEquals.listEquals(us_CFTC_PR_EligibleCollateral, _that.getUs_CFTC_PR_EligibleCollateral())) return false;
			if (!ListEquals.listEquals(nonEnumeratedTaxonomyValue, _that.getNonEnumeratedTaxonomyValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (eu_EMIR_EligibleCollateral != null ? eu_EMIR_EligibleCollateral.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (uk_EMIR_EligibleCollateral != null ? uk_EMIR_EligibleCollateral.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (us_CFTC_PR_EligibleCollateral != null ? us_CFTC_PR_EligibleCollateral.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (nonEnumeratedTaxonomyValue != null ? nonEnumeratedTaxonomyValue.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralTaxonomyValue {" +
				"eu_EMIR_EligibleCollateral=" + this.eu_EMIR_EligibleCollateral + ", " +
				"uk_EMIR_EligibleCollateral=" + this.uk_EMIR_EligibleCollateral + ", " +
				"us_CFTC_PR_EligibleCollateral=" + this.us_CFTC_PR_EligibleCollateral + ", " +
				"nonEnumeratedTaxonomyValue=" + this.nonEnumeratedTaxonomyValue +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralTaxonomyValue  ***********************/
	class CollateralTaxonomyValueBuilderImpl implements CollateralTaxonomyValue.CollateralTaxonomyValueBuilder {
	
		protected List<EU_EMIR_EligibleCollateralEnum> eu_EMIR_EligibleCollateral = new ArrayList<>();
		protected List<UK_EMIR_EligibleCollateralEnum> uk_EMIR_EligibleCollateral = new ArrayList<>();
		protected List<US_CFTC_PR_EligibleCollateralEnum> us_CFTC_PR_EligibleCollateral = new ArrayList<>();
		protected List<FieldWithMetaString.FieldWithMetaStringBuilder> nonEnumeratedTaxonomyValue = new ArrayList<>();
	
		public CollateralTaxonomyValueBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("eu_EMIR_EligibleCollateral")
		public List<EU_EMIR_EligibleCollateralEnum> getEu_EMIR_EligibleCollateral() {
			return eu_EMIR_EligibleCollateral;
		}
		
		@Override
		@RosettaAttribute("uk_EMIR_EligibleCollateral")
		public List<UK_EMIR_EligibleCollateralEnum> getUk_EMIR_EligibleCollateral() {
			return uk_EMIR_EligibleCollateral;
		}
		
		@Override
		@RosettaAttribute("us_CFTC_PR_EligibleCollateral")
		public List<US_CFTC_PR_EligibleCollateralEnum> getUs_CFTC_PR_EligibleCollateral() {
			return us_CFTC_PR_EligibleCollateral;
		}
		
		@Override
		@RosettaAttribute("nonEnumeratedTaxonomyValue")
		public List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getNonEnumeratedTaxonomyValue() {
			return nonEnumeratedTaxonomyValue;
		}
		
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateNonEnumeratedTaxonomyValue(int _index) {
		
			if (nonEnumeratedTaxonomyValue==null) {
				this.nonEnumeratedTaxonomyValue = new ArrayList<>();
			}
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			return getIndex(nonEnumeratedTaxonomyValue, _index, () -> {
						FieldWithMetaString.FieldWithMetaStringBuilder newNonEnumeratedTaxonomyValue = FieldWithMetaString.builder();
						return newNonEnumeratedTaxonomyValue;
					});
		}
		
	
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addEu_EMIR_EligibleCollateral(EU_EMIR_EligibleCollateralEnum eu_EMIR_EligibleCollateral) {
			if (eu_EMIR_EligibleCollateral!=null) this.eu_EMIR_EligibleCollateral.add(eu_EMIR_EligibleCollateral);
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addEu_EMIR_EligibleCollateral(EU_EMIR_EligibleCollateralEnum eu_EMIR_EligibleCollateral, int _idx) {
			getIndex(this.eu_EMIR_EligibleCollateral, _idx, () -> eu_EMIR_EligibleCollateral);
			return this;
		}
		@Override 
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addEu_EMIR_EligibleCollateral(List<? extends EU_EMIR_EligibleCollateralEnum> eu_EMIR_EligibleCollaterals) {
			if (eu_EMIR_EligibleCollaterals != null) {
				for (EU_EMIR_EligibleCollateralEnum toAdd : eu_EMIR_EligibleCollaterals) {
					this.eu_EMIR_EligibleCollateral.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("eu_EMIR_EligibleCollateral")
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder setEu_EMIR_EligibleCollateral(List<? extends EU_EMIR_EligibleCollateralEnum> eu_EMIR_EligibleCollaterals) {
			if (eu_EMIR_EligibleCollaterals == null)  {
				this.eu_EMIR_EligibleCollateral = new ArrayList<>();
			}
			else {
				this.eu_EMIR_EligibleCollateral = eu_EMIR_EligibleCollaterals.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUk_EMIR_EligibleCollateral(UK_EMIR_EligibleCollateralEnum uk_EMIR_EligibleCollateral) {
			if (uk_EMIR_EligibleCollateral!=null) this.uk_EMIR_EligibleCollateral.add(uk_EMIR_EligibleCollateral);
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUk_EMIR_EligibleCollateral(UK_EMIR_EligibleCollateralEnum uk_EMIR_EligibleCollateral, int _idx) {
			getIndex(this.uk_EMIR_EligibleCollateral, _idx, () -> uk_EMIR_EligibleCollateral);
			return this;
		}
		@Override 
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUk_EMIR_EligibleCollateral(List<? extends UK_EMIR_EligibleCollateralEnum> uk_EMIR_EligibleCollaterals) {
			if (uk_EMIR_EligibleCollaterals != null) {
				for (UK_EMIR_EligibleCollateralEnum toAdd : uk_EMIR_EligibleCollaterals) {
					this.uk_EMIR_EligibleCollateral.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("uk_EMIR_EligibleCollateral")
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder setUk_EMIR_EligibleCollateral(List<? extends UK_EMIR_EligibleCollateralEnum> uk_EMIR_EligibleCollaterals) {
			if (uk_EMIR_EligibleCollaterals == null)  {
				this.uk_EMIR_EligibleCollateral = new ArrayList<>();
			}
			else {
				this.uk_EMIR_EligibleCollateral = uk_EMIR_EligibleCollaterals.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUs_CFTC_PR_EligibleCollateral(US_CFTC_PR_EligibleCollateralEnum us_CFTC_PR_EligibleCollateral) {
			if (us_CFTC_PR_EligibleCollateral!=null) this.us_CFTC_PR_EligibleCollateral.add(us_CFTC_PR_EligibleCollateral);
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUs_CFTC_PR_EligibleCollateral(US_CFTC_PR_EligibleCollateralEnum us_CFTC_PR_EligibleCollateral, int _idx) {
			getIndex(this.us_CFTC_PR_EligibleCollateral, _idx, () -> us_CFTC_PR_EligibleCollateral);
			return this;
		}
		@Override 
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addUs_CFTC_PR_EligibleCollateral(List<? extends US_CFTC_PR_EligibleCollateralEnum> us_CFTC_PR_EligibleCollaterals) {
			if (us_CFTC_PR_EligibleCollaterals != null) {
				for (US_CFTC_PR_EligibleCollateralEnum toAdd : us_CFTC_PR_EligibleCollaterals) {
					this.us_CFTC_PR_EligibleCollateral.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("us_CFTC_PR_EligibleCollateral")
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder setUs_CFTC_PR_EligibleCollateral(List<? extends US_CFTC_PR_EligibleCollateralEnum> us_CFTC_PR_EligibleCollaterals) {
			if (us_CFTC_PR_EligibleCollaterals == null)  {
				this.us_CFTC_PR_EligibleCollateral = new ArrayList<>();
			}
			else {
				this.us_CFTC_PR_EligibleCollateral = us_CFTC_PR_EligibleCollaterals.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValue(FieldWithMetaString nonEnumeratedTaxonomyValue) {
			if (nonEnumeratedTaxonomyValue!=null) this.nonEnumeratedTaxonomyValue.add(nonEnumeratedTaxonomyValue.toBuilder());
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValue(FieldWithMetaString nonEnumeratedTaxonomyValue, int _idx) {
			getIndex(this.nonEnumeratedTaxonomyValue, _idx, () -> nonEnumeratedTaxonomyValue.toBuilder());
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValueValue(String nonEnumeratedTaxonomyValue) {
			this.getOrCreateNonEnumeratedTaxonomyValue(-1).setValue(nonEnumeratedTaxonomyValue);
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValueValue(String nonEnumeratedTaxonomyValue, int _idx) {
			this.getOrCreateNonEnumeratedTaxonomyValue(_idx).setValue(nonEnumeratedTaxonomyValue);
			return this;
		}
		@Override 
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValue(List<? extends FieldWithMetaString> nonEnumeratedTaxonomyValues) {
			if (nonEnumeratedTaxonomyValues != null) {
				for (FieldWithMetaString toAdd : nonEnumeratedTaxonomyValues) {
					this.nonEnumeratedTaxonomyValue.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("nonEnumeratedTaxonomyValue")
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder setNonEnumeratedTaxonomyValue(List<? extends FieldWithMetaString> nonEnumeratedTaxonomyValues) {
			if (nonEnumeratedTaxonomyValues == null)  {
				this.nonEnumeratedTaxonomyValue = new ArrayList<>();
			}
			else {
				this.nonEnumeratedTaxonomyValue = nonEnumeratedTaxonomyValues.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder addNonEnumeratedTaxonomyValueValue(List<? extends String> nonEnumeratedTaxonomyValues) {
			if (nonEnumeratedTaxonomyValues != null) {
				for (String toAdd : nonEnumeratedTaxonomyValues) {
					this.addNonEnumeratedTaxonomyValueValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder setNonEnumeratedTaxonomyValueValue(List<? extends String> nonEnumeratedTaxonomyValues) {
			this.nonEnumeratedTaxonomyValue.clear();
			if (nonEnumeratedTaxonomyValues!=null) {
				nonEnumeratedTaxonomyValues.forEach(this::addNonEnumeratedTaxonomyValueValue);
			}
			return this;
		}
		
		
		@Override
		public CollateralTaxonomyValue build() {
			return new CollateralTaxonomyValue.CollateralTaxonomyValueImpl(this);
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder prune() {
			nonEnumeratedTaxonomyValue = nonEnumeratedTaxonomyValue.stream().filter(b->b!=null).<FieldWithMetaString.FieldWithMetaStringBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getEu_EMIR_EligibleCollateral()!=null && !getEu_EMIR_EligibleCollateral().isEmpty()) return true;
			if (getUk_EMIR_EligibleCollateral()!=null && !getUk_EMIR_EligibleCollateral().isEmpty()) return true;
			if (getUs_CFTC_PR_EligibleCollateral()!=null && !getUs_CFTC_PR_EligibleCollateral().isEmpty()) return true;
			if (getNonEnumeratedTaxonomyValue()!=null && !getNonEnumeratedTaxonomyValue().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralTaxonomyValue.CollateralTaxonomyValueBuilder o = (CollateralTaxonomyValue.CollateralTaxonomyValueBuilder) other;
			
			merger.mergeRosetta(getNonEnumeratedTaxonomyValue(), o.getNonEnumeratedTaxonomyValue(), this::getOrCreateNonEnumeratedTaxonomyValue);
			
			merger.mergeBasic(getEu_EMIR_EligibleCollateral(), o.getEu_EMIR_EligibleCollateral(), (Consumer<EU_EMIR_EligibleCollateralEnum>) this::addEu_EMIR_EligibleCollateral);
			merger.mergeBasic(getUk_EMIR_EligibleCollateral(), o.getUk_EMIR_EligibleCollateral(), (Consumer<UK_EMIR_EligibleCollateralEnum>) this::addUk_EMIR_EligibleCollateral);
			merger.mergeBasic(getUs_CFTC_PR_EligibleCollateral(), o.getUs_CFTC_PR_EligibleCollateral(), (Consumer<US_CFTC_PR_EligibleCollateralEnum>) this::addUs_CFTC_PR_EligibleCollateral);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralTaxonomyValue _that = getType().cast(o);
		
			if (!ListEquals.listEquals(eu_EMIR_EligibleCollateral, _that.getEu_EMIR_EligibleCollateral())) return false;
			if (!ListEquals.listEquals(uk_EMIR_EligibleCollateral, _that.getUk_EMIR_EligibleCollateral())) return false;
			if (!ListEquals.listEquals(us_CFTC_PR_EligibleCollateral, _that.getUs_CFTC_PR_EligibleCollateral())) return false;
			if (!ListEquals.listEquals(nonEnumeratedTaxonomyValue, _that.getNonEnumeratedTaxonomyValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (eu_EMIR_EligibleCollateral != null ? eu_EMIR_EligibleCollateral.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (uk_EMIR_EligibleCollateral != null ? uk_EMIR_EligibleCollateral.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (us_CFTC_PR_EligibleCollateral != null ? us_CFTC_PR_EligibleCollateral.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (nonEnumeratedTaxonomyValue != null ? nonEnumeratedTaxonomyValue.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralTaxonomyValueBuilder {" +
				"eu_EMIR_EligibleCollateral=" + this.eu_EMIR_EligibleCollateral + ", " +
				"uk_EMIR_EligibleCollateral=" + this.uk_EMIR_EligibleCollateral + ", " +
				"us_CFTC_PR_EligibleCollateral=" + this.us_CFTC_PR_EligibleCollateral + ", " +
				"nonEnumeratedTaxonomyValue=" + this.nonEnumeratedTaxonomyValue +
			'}';
		}
	}
}
