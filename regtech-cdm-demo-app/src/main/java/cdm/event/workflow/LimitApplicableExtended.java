package cdm.event.workflow;

import cdm.event.workflow.CreditLimitTypeEnum;
import cdm.event.workflow.CreditLimitUtilisation;
import cdm.event.workflow.LimitApplicable;
import cdm.event.workflow.LimitApplicable.LimitApplicableBuilder;
import cdm.event.workflow.LimitApplicable.LimitApplicableBuilderImpl;
import cdm.event.workflow.LimitApplicable.LimitApplicableImpl;
import cdm.event.workflow.LimitApplicableExtended;
import cdm.event.workflow.LimitApplicableExtended.LimitApplicableExtendedBuilder;
import cdm.event.workflow.LimitApplicableExtended.LimitApplicableExtendedBuilderImpl;
import cdm.event.workflow.LimitApplicableExtended.LimitApplicableExtendedImpl;
import cdm.event.workflow.LimitLevelEnum;
import cdm.event.workflow.Velocity;
import cdm.event.workflow.meta.LimitApplicableExtendedMeta;
import cdm.event.workflow.metafields.FieldWithMetaCreditLimitTypeEnum;
import cdm.event.workflow.metafields.FieldWithMetaCreditLimitTypeEnum.FieldWithMetaCreditLimitTypeEnumBuilder;
import cdm.event.workflow.metafields.FieldWithMetaLimitLevelEnum;
import cdm.event.workflow.metafields.FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder;
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
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to represent the CDM attributes that are not part of the FpML standard. Once broader usage is confirmed, it is expected that those two classes can be collapsed.
 * @version ${project.version}
 */
@RosettaDataType(value="LimitApplicableExtended", builder=LimitApplicableExtended.LimitApplicableExtendedBuilderImpl.class, version="${project.version}")
public interface LimitApplicableExtended extends LimitApplicable {

	LimitApplicableExtendedMeta metaData = new LimitApplicableExtendedMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The level at which the limit is set: customer business, proprietary business or account level. This attribute is specified as a string as part of the CME clearing confirmation specification.
	 */
	FieldWithMetaLimitLevelEnum getLimitLevel();
	/**
	 * The total limit available for the limit level and limit type. While the attribute is of type integer in the CME schema, it has been specified to be of type number in the CDM to take into consideration java size limits as well as for consistency purposes with the way most monetary amounts are expressed.
	 */
	BigDecimal getLimitAmount();
	/**
	 * The limit utilized by this specific trade. While the attribute is of type integer in the CME schema, it has been specified to be of type number in the CDM to take into consideration java size limits as well as for consistency purposes with the way most monetary amounts are expressed.
	 */
	BigDecimal getLimitImpactDueToTrade();

	/*********************** Build Methods  ***********************/
	LimitApplicableExtended build();
	
	LimitApplicableExtended.LimitApplicableExtendedBuilder toBuilder();
	
	static LimitApplicableExtended.LimitApplicableExtendedBuilder builder() {
		return new LimitApplicableExtended.LimitApplicableExtendedBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends LimitApplicableExtended> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends LimitApplicableExtended> getType() {
		return LimitApplicableExtended.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("limitType"), processor, FieldWithMetaCreditLimitTypeEnum.class, getLimitType());
		processor.processBasic(path.newSubPath("clipSize"), Integer.class, getClipSize(), this);
		processor.processBasic(path.newSubPath("amountUtilized"), BigDecimal.class, getAmountUtilized(), this);
		processRosetta(path.newSubPath("utilization"), processor, CreditLimitUtilisation.class, getUtilization());
		processor.processBasic(path.newSubPath("amountRemaining"), BigDecimal.class, getAmountRemaining(), this);
		processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.class, getCurrency());
		processRosetta(path.newSubPath("velocity"), processor, Velocity.class, getVelocity());
		processRosetta(path.newSubPath("limitLevel"), processor, FieldWithMetaLimitLevelEnum.class, getLimitLevel());
		processor.processBasic(path.newSubPath("limitAmount"), BigDecimal.class, getLimitAmount(), this);
		processor.processBasic(path.newSubPath("limitImpactDueToTrade"), BigDecimal.class, getLimitImpactDueToTrade(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface LimitApplicableExtendedBuilder extends LimitApplicableExtended, LimitApplicable.LimitApplicableBuilder, RosettaModelObjectBuilder {
		FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder getOrCreateLimitLevel();
		FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder getLimitLevel();
		LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitType(FieldWithMetaCreditLimitTypeEnum limitType0);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitTypeValue(CreditLimitTypeEnum limitType1);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setClipSize(Integer clipSize);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setAmountUtilized(BigDecimal amountUtilized);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setUtilization(CreditLimitUtilisation utilization);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setAmountRemaining(BigDecimal amountRemaining);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setCurrency(FieldWithMetaString currency0);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setCurrencyValue(String currency1);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setVelocity(Velocity velocity);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitLevel(FieldWithMetaLimitLevelEnum limitLevel0);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitLevelValue(LimitLevelEnum limitLevel1);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitAmount(BigDecimal limitAmount);
		LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitImpactDueToTrade(BigDecimal limitImpactDueToTrade);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("limitType"), processor, FieldWithMetaCreditLimitTypeEnum.FieldWithMetaCreditLimitTypeEnumBuilder.class, getLimitType());
			processor.processBasic(path.newSubPath("clipSize"), Integer.class, getClipSize(), this);
			processor.processBasic(path.newSubPath("amountUtilized"), BigDecimal.class, getAmountUtilized(), this);
			processRosetta(path.newSubPath("utilization"), processor, CreditLimitUtilisation.CreditLimitUtilisationBuilder.class, getUtilization());
			processor.processBasic(path.newSubPath("amountRemaining"), BigDecimal.class, getAmountRemaining(), this);
			processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCurrency());
			processRosetta(path.newSubPath("velocity"), processor, Velocity.VelocityBuilder.class, getVelocity());
			processRosetta(path.newSubPath("limitLevel"), processor, FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder.class, getLimitLevel());
			processor.processBasic(path.newSubPath("limitAmount"), BigDecimal.class, getLimitAmount(), this);
			processor.processBasic(path.newSubPath("limitImpactDueToTrade"), BigDecimal.class, getLimitImpactDueToTrade(), this);
		}
		

		LimitApplicableExtended.LimitApplicableExtendedBuilder prune();
	}

	/*********************** Immutable Implementation of LimitApplicableExtended  ***********************/
	class LimitApplicableExtendedImpl extends LimitApplicable.LimitApplicableImpl implements LimitApplicableExtended {
		private final FieldWithMetaLimitLevelEnum limitLevel;
		private final BigDecimal limitAmount;
		private final BigDecimal limitImpactDueToTrade;
		
		protected LimitApplicableExtendedImpl(LimitApplicableExtended.LimitApplicableExtendedBuilder builder) {
			super(builder);
			this.limitLevel = ofNullable(builder.getLimitLevel()).map(f->f.build()).orElse(null);
			this.limitAmount = builder.getLimitAmount();
			this.limitImpactDueToTrade = builder.getLimitImpactDueToTrade();
		}
		
		@Override
		@RosettaAttribute("limitLevel")
		public FieldWithMetaLimitLevelEnum getLimitLevel() {
			return limitLevel;
		}
		
		@Override
		@RosettaAttribute("limitAmount")
		public BigDecimal getLimitAmount() {
			return limitAmount;
		}
		
		@Override
		@RosettaAttribute("limitImpactDueToTrade")
		public BigDecimal getLimitImpactDueToTrade() {
			return limitImpactDueToTrade;
		}
		
		@Override
		public LimitApplicableExtended build() {
			return this;
		}
		
		@Override
		public LimitApplicableExtended.LimitApplicableExtendedBuilder toBuilder() {
			LimitApplicableExtended.LimitApplicableExtendedBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(LimitApplicableExtended.LimitApplicableExtendedBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getLimitLevel()).ifPresent(builder::setLimitLevel);
			ofNullable(getLimitAmount()).ifPresent(builder::setLimitAmount);
			ofNullable(getLimitImpactDueToTrade()).ifPresent(builder::setLimitImpactDueToTrade);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			LimitApplicableExtended _that = getType().cast(o);
		
			if (!Objects.equals(limitLevel, _that.getLimitLevel())) return false;
			if (!Objects.equals(limitAmount, _that.getLimitAmount())) return false;
			if (!Objects.equals(limitImpactDueToTrade, _that.getLimitImpactDueToTrade())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (limitLevel != null ? limitLevel.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (limitAmount != null ? limitAmount.hashCode() : 0);
			_result = 31 * _result + (limitImpactDueToTrade != null ? limitImpactDueToTrade.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LimitApplicableExtended {" +
				"limitLevel=" + this.limitLevel + ", " +
				"limitAmount=" + this.limitAmount + ", " +
				"limitImpactDueToTrade=" + this.limitImpactDueToTrade +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of LimitApplicableExtended  ***********************/
	class LimitApplicableExtendedBuilderImpl extends LimitApplicable.LimitApplicableBuilderImpl  implements LimitApplicableExtended.LimitApplicableExtendedBuilder {
	
		protected FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder limitLevel;
		protected BigDecimal limitAmount;
		protected BigDecimal limitImpactDueToTrade;
	
		public LimitApplicableExtendedBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("limitLevel")
		public FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder getLimitLevel() {
			return limitLevel;
		}
		
		@Override
		public FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder getOrCreateLimitLevel() {
			FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder result;
			if (limitLevel!=null) {
				result = limitLevel;
			}
			else {
				result = limitLevel = FieldWithMetaLimitLevelEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("limitAmount")
		public BigDecimal getLimitAmount() {
			return limitAmount;
		}
		
		@Override
		@RosettaAttribute("limitImpactDueToTrade")
		public BigDecimal getLimitImpactDueToTrade() {
			return limitImpactDueToTrade;
		}
		
	
		@Override
		@RosettaAttribute("limitType")
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitType(FieldWithMetaCreditLimitTypeEnum limitType) {
			this.limitType = limitType==null?null:limitType.toBuilder();
			return this;
		}
		@Override
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitTypeValue(CreditLimitTypeEnum limitType) {
			this.getOrCreateLimitType().setValue(limitType);
			return this;
		}
		@Override
		@RosettaAttribute("clipSize")
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setClipSize(Integer clipSize) {
			this.clipSize = clipSize==null?null:clipSize;
			return this;
		}
		@Override
		@RosettaAttribute("amountUtilized")
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setAmountUtilized(BigDecimal amountUtilized) {
			this.amountUtilized = amountUtilized==null?null:amountUtilized;
			return this;
		}
		@Override
		@RosettaAttribute("utilization")
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setUtilization(CreditLimitUtilisation utilization) {
			this.utilization = utilization==null?null:utilization.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("amountRemaining")
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setAmountRemaining(BigDecimal amountRemaining) {
			this.amountRemaining = amountRemaining==null?null:amountRemaining;
			return this;
		}
		@Override
		@RosettaAttribute("currency")
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setCurrency(FieldWithMetaString currency) {
			this.currency = currency==null?null:currency.toBuilder();
			return this;
		}
		@Override
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setCurrencyValue(String currency) {
			this.getOrCreateCurrency().setValue(currency);
			return this;
		}
		@Override
		@RosettaAttribute("velocity")
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setVelocity(Velocity velocity) {
			this.velocity = velocity==null?null:velocity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("limitLevel")
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitLevel(FieldWithMetaLimitLevelEnum limitLevel) {
			this.limitLevel = limitLevel==null?null:limitLevel.toBuilder();
			return this;
		}
		@Override
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitLevelValue(LimitLevelEnum limitLevel) {
			this.getOrCreateLimitLevel().setValue(limitLevel);
			return this;
		}
		@Override
		@RosettaAttribute("limitAmount")
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitAmount(BigDecimal limitAmount) {
			this.limitAmount = limitAmount==null?null:limitAmount;
			return this;
		}
		@Override
		@RosettaAttribute("limitImpactDueToTrade")
		public LimitApplicableExtended.LimitApplicableExtendedBuilder setLimitImpactDueToTrade(BigDecimal limitImpactDueToTrade) {
			this.limitImpactDueToTrade = limitImpactDueToTrade==null?null:limitImpactDueToTrade;
			return this;
		}
		
		@Override
		public LimitApplicableExtended build() {
			return new LimitApplicableExtended.LimitApplicableExtendedImpl(this);
		}
		
		@Override
		public LimitApplicableExtended.LimitApplicableExtendedBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LimitApplicableExtended.LimitApplicableExtendedBuilder prune() {
			super.prune();
			if (limitLevel!=null && !limitLevel.prune().hasData()) limitLevel = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getLimitLevel()!=null) return true;
			if (getLimitAmount()!=null) return true;
			if (getLimitImpactDueToTrade()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LimitApplicableExtended.LimitApplicableExtendedBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			LimitApplicableExtended.LimitApplicableExtendedBuilder o = (LimitApplicableExtended.LimitApplicableExtendedBuilder) other;
			
			merger.mergeRosetta(getLimitLevel(), o.getLimitLevel(), this::setLimitLevel);
			
			merger.mergeBasic(getLimitAmount(), o.getLimitAmount(), this::setLimitAmount);
			merger.mergeBasic(getLimitImpactDueToTrade(), o.getLimitImpactDueToTrade(), this::setLimitImpactDueToTrade);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			LimitApplicableExtended _that = getType().cast(o);
		
			if (!Objects.equals(limitLevel, _that.getLimitLevel())) return false;
			if (!Objects.equals(limitAmount, _that.getLimitAmount())) return false;
			if (!Objects.equals(limitImpactDueToTrade, _that.getLimitImpactDueToTrade())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (limitLevel != null ? limitLevel.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (limitAmount != null ? limitAmount.hashCode() : 0);
			_result = 31 * _result + (limitImpactDueToTrade != null ? limitImpactDueToTrade.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LimitApplicableExtendedBuilder {" +
				"limitLevel=" + this.limitLevel + ", " +
				"limitAmount=" + this.limitAmount + ", " +
				"limitImpactDueToTrade=" + this.limitImpactDueToTrade +
			'}' + " " + super.toString();
		}
	}
}
