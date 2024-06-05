package cdm.product.template;

import cdm.observable.asset.Money;
import cdm.product.template.InitialMargin;
import cdm.product.template.InitialMargin.InitialMarginBuilder;
import cdm.product.template.InitialMargin.InitialMarginBuilderImpl;
import cdm.product.template.InitialMargin.InitialMarginImpl;
import cdm.product.template.InitialMarginCalculation;
import cdm.product.template.MarginTypeEnum;
import cdm.product.template.meta.InitialMarginMeta;
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
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 *  Defines initial margin applied to a repo transaction. Initial margin is an agreed premium to the Purchase Price of a repo to determine the required Market Value of the collateral to be delivered on the Purchase Date. It reflects quality of the collateral. Its aim is to calculate the risk-adjusted or liquidation value of collateral.
 * @version ${project.version}
 */
@RosettaDataType(value="InitialMargin", builder=InitialMargin.InitialMarginBuilderImpl.class, version="${project.version}")
public interface InitialMargin extends RosettaModelObject {

	InitialMarginMeta metaData = new InitialMarginMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An element defining the type of assets (cash or securities) specified to apply as margin to the repo transaction. See GMRA 2011 paragraph 2(h) for &#39;Cash Margin&#39; and GMRA 2011 paragraph 2(cc) for &#39;Margin Securities&#39;.
	 */
	MarginTypeEnum getMarginType();
	/**
	 * Initial margin calculation for a collateral asset. Initial margin requirements may be specified for multiple pieces of collateral.
	 */
	List<? extends InitialMarginCalculation> getMargin();
	/**
	 * An element defining a margin threshold which is the Net Exposure of a trade below which parties agree they will not call a margin from each other.
	 */
	Money getMarginThreshold();
	/**
	 * An element defining a minimum transfer amount which is the minimum margin call parties will make once the margin threshold (or margin ratio threshold / haircut threshold) has been exceeded.
	 */
	Money getMinimumTransferAmount();

	/*********************** Build Methods  ***********************/
	InitialMargin build();
	
	InitialMargin.InitialMarginBuilder toBuilder();
	
	static InitialMargin.InitialMarginBuilder builder() {
		return new InitialMargin.InitialMarginBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends InitialMargin> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends InitialMargin> getType() {
		return InitialMargin.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("marginType"), MarginTypeEnum.class, getMarginType(), this);
		processRosetta(path.newSubPath("margin"), processor, InitialMarginCalculation.class, getMargin());
		processRosetta(path.newSubPath("marginThreshold"), processor, Money.class, getMarginThreshold());
		processRosetta(path.newSubPath("minimumTransferAmount"), processor, Money.class, getMinimumTransferAmount());
	}
	

	/*********************** Builder Interface  ***********************/
	interface InitialMarginBuilder extends InitialMargin, RosettaModelObjectBuilder {
		InitialMarginCalculation.InitialMarginCalculationBuilder getOrCreateMargin(int _index);
		List<? extends InitialMarginCalculation.InitialMarginCalculationBuilder> getMargin();
		Money.MoneyBuilder getOrCreateMarginThreshold();
		Money.MoneyBuilder getMarginThreshold();
		Money.MoneyBuilder getOrCreateMinimumTransferAmount();
		Money.MoneyBuilder getMinimumTransferAmount();
		InitialMargin.InitialMarginBuilder setMarginType(MarginTypeEnum marginType);
		InitialMargin.InitialMarginBuilder addMargin(InitialMarginCalculation margin0);
		InitialMargin.InitialMarginBuilder addMargin(InitialMarginCalculation margin1, int _idx);
		InitialMargin.InitialMarginBuilder addMargin(List<? extends InitialMarginCalculation> margin2);
		InitialMargin.InitialMarginBuilder setMargin(List<? extends InitialMarginCalculation> margin3);
		InitialMargin.InitialMarginBuilder setMarginThreshold(Money marginThreshold);
		InitialMargin.InitialMarginBuilder setMinimumTransferAmount(Money minimumTransferAmount);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("marginType"), MarginTypeEnum.class, getMarginType(), this);
			processRosetta(path.newSubPath("margin"), processor, InitialMarginCalculation.InitialMarginCalculationBuilder.class, getMargin());
			processRosetta(path.newSubPath("marginThreshold"), processor, Money.MoneyBuilder.class, getMarginThreshold());
			processRosetta(path.newSubPath("minimumTransferAmount"), processor, Money.MoneyBuilder.class, getMinimumTransferAmount());
		}
		

		InitialMargin.InitialMarginBuilder prune();
	}

	/*********************** Immutable Implementation of InitialMargin  ***********************/
	class InitialMarginImpl implements InitialMargin {
		private final MarginTypeEnum marginType;
		private final List<? extends InitialMarginCalculation> margin;
		private final Money marginThreshold;
		private final Money minimumTransferAmount;
		
		protected InitialMarginImpl(InitialMargin.InitialMarginBuilder builder) {
			this.marginType = builder.getMarginType();
			this.margin = ofNullable(builder.getMargin()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.marginThreshold = ofNullable(builder.getMarginThreshold()).map(f->f.build()).orElse(null);
			this.minimumTransferAmount = ofNullable(builder.getMinimumTransferAmount()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("marginType")
		public MarginTypeEnum getMarginType() {
			return marginType;
		}
		
		@Override
		@RosettaAttribute("margin")
		public List<? extends InitialMarginCalculation> getMargin() {
			return margin;
		}
		
		@Override
		@RosettaAttribute("marginThreshold")
		public Money getMarginThreshold() {
			return marginThreshold;
		}
		
		@Override
		@RosettaAttribute("minimumTransferAmount")
		public Money getMinimumTransferAmount() {
			return minimumTransferAmount;
		}
		
		@Override
		public InitialMargin build() {
			return this;
		}
		
		@Override
		public InitialMargin.InitialMarginBuilder toBuilder() {
			InitialMargin.InitialMarginBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(InitialMargin.InitialMarginBuilder builder) {
			ofNullable(getMarginType()).ifPresent(builder::setMarginType);
			ofNullable(getMargin()).ifPresent(builder::setMargin);
			ofNullable(getMarginThreshold()).ifPresent(builder::setMarginThreshold);
			ofNullable(getMinimumTransferAmount()).ifPresent(builder::setMinimumTransferAmount);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InitialMargin _that = getType().cast(o);
		
			if (!Objects.equals(marginType, _that.getMarginType())) return false;
			if (!ListEquals.listEquals(margin, _that.getMargin())) return false;
			if (!Objects.equals(marginThreshold, _that.getMarginThreshold())) return false;
			if (!Objects.equals(minimumTransferAmount, _that.getMinimumTransferAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (marginType != null ? marginType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (margin != null ? margin.hashCode() : 0);
			_result = 31 * _result + (marginThreshold != null ? marginThreshold.hashCode() : 0);
			_result = 31 * _result + (minimumTransferAmount != null ? minimumTransferAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InitialMargin {" +
				"marginType=" + this.marginType + ", " +
				"margin=" + this.margin + ", " +
				"marginThreshold=" + this.marginThreshold + ", " +
				"minimumTransferAmount=" + this.minimumTransferAmount +
			'}';
		}
	}

	/*********************** Builder Implementation of InitialMargin  ***********************/
	class InitialMarginBuilderImpl implements InitialMargin.InitialMarginBuilder {
	
		protected MarginTypeEnum marginType;
		protected List<InitialMarginCalculation.InitialMarginCalculationBuilder> margin = new ArrayList<>();
		protected Money.MoneyBuilder marginThreshold;
		protected Money.MoneyBuilder minimumTransferAmount;
	
		public InitialMarginBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("marginType")
		public MarginTypeEnum getMarginType() {
			return marginType;
		}
		
		@Override
		@RosettaAttribute("margin")
		public List<? extends InitialMarginCalculation.InitialMarginCalculationBuilder> getMargin() {
			return margin;
		}
		
		public InitialMarginCalculation.InitialMarginCalculationBuilder getOrCreateMargin(int _index) {
		
			if (margin==null) {
				this.margin = new ArrayList<>();
			}
			InitialMarginCalculation.InitialMarginCalculationBuilder result;
			return getIndex(margin, _index, () -> {
						InitialMarginCalculation.InitialMarginCalculationBuilder newMargin = InitialMarginCalculation.builder();
						return newMargin;
					});
		}
		
		@Override
		@RosettaAttribute("marginThreshold")
		public Money.MoneyBuilder getMarginThreshold() {
			return marginThreshold;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateMarginThreshold() {
			Money.MoneyBuilder result;
			if (marginThreshold!=null) {
				result = marginThreshold;
			}
			else {
				result = marginThreshold = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("minimumTransferAmount")
		public Money.MoneyBuilder getMinimumTransferAmount() {
			return minimumTransferAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateMinimumTransferAmount() {
			Money.MoneyBuilder result;
			if (minimumTransferAmount!=null) {
				result = minimumTransferAmount;
			}
			else {
				result = minimumTransferAmount = Money.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("marginType")
		public InitialMargin.InitialMarginBuilder setMarginType(MarginTypeEnum marginType) {
			this.marginType = marginType==null?null:marginType;
			return this;
		}
		@Override
		public InitialMargin.InitialMarginBuilder addMargin(InitialMarginCalculation margin) {
			if (margin!=null) this.margin.add(margin.toBuilder());
			return this;
		}
		
		@Override
		public InitialMargin.InitialMarginBuilder addMargin(InitialMarginCalculation margin, int _idx) {
			getIndex(this.margin, _idx, () -> margin.toBuilder());
			return this;
		}
		@Override 
		public InitialMargin.InitialMarginBuilder addMargin(List<? extends InitialMarginCalculation> margins) {
			if (margins != null) {
				for (InitialMarginCalculation toAdd : margins) {
					this.margin.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("margin")
		public InitialMargin.InitialMarginBuilder setMargin(List<? extends InitialMarginCalculation> margins) {
			if (margins == null)  {
				this.margin = new ArrayList<>();
			}
			else {
				this.margin = margins.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("marginThreshold")
		public InitialMargin.InitialMarginBuilder setMarginThreshold(Money marginThreshold) {
			this.marginThreshold = marginThreshold==null?null:marginThreshold.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("minimumTransferAmount")
		public InitialMargin.InitialMarginBuilder setMinimumTransferAmount(Money minimumTransferAmount) {
			this.minimumTransferAmount = minimumTransferAmount==null?null:minimumTransferAmount.toBuilder();
			return this;
		}
		
		@Override
		public InitialMargin build() {
			return new InitialMargin.InitialMarginImpl(this);
		}
		
		@Override
		public InitialMargin.InitialMarginBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InitialMargin.InitialMarginBuilder prune() {
			margin = margin.stream().filter(b->b!=null).<InitialMarginCalculation.InitialMarginCalculationBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (marginThreshold!=null && !marginThreshold.prune().hasData()) marginThreshold = null;
			if (minimumTransferAmount!=null && !minimumTransferAmount.prune().hasData()) minimumTransferAmount = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMarginType()!=null) return true;
			if (getMargin()!=null && getMargin().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getMarginThreshold()!=null && getMarginThreshold().hasData()) return true;
			if (getMinimumTransferAmount()!=null && getMinimumTransferAmount().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InitialMargin.InitialMarginBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			InitialMargin.InitialMarginBuilder o = (InitialMargin.InitialMarginBuilder) other;
			
			merger.mergeRosetta(getMargin(), o.getMargin(), this::getOrCreateMargin);
			merger.mergeRosetta(getMarginThreshold(), o.getMarginThreshold(), this::setMarginThreshold);
			merger.mergeRosetta(getMinimumTransferAmount(), o.getMinimumTransferAmount(), this::setMinimumTransferAmount);
			
			merger.mergeBasic(getMarginType(), o.getMarginType(), this::setMarginType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InitialMargin _that = getType().cast(o);
		
			if (!Objects.equals(marginType, _that.getMarginType())) return false;
			if (!ListEquals.listEquals(margin, _that.getMargin())) return false;
			if (!Objects.equals(marginThreshold, _that.getMarginThreshold())) return false;
			if (!Objects.equals(minimumTransferAmount, _that.getMinimumTransferAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (marginType != null ? marginType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (margin != null ? margin.hashCode() : 0);
			_result = 31 * _result + (marginThreshold != null ? marginThreshold.hashCode() : 0);
			_result = 31 * _result + (minimumTransferAmount != null ? minimumTransferAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InitialMarginBuilder {" +
				"marginType=" + this.marginType + ", " +
				"margin=" + this.margin + ", " +
				"marginThreshold=" + this.marginThreshold + ", " +
				"minimumTransferAmount=" + this.minimumTransferAmount +
			'}';
		}
	}
}
