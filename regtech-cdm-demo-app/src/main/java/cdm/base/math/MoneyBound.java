package cdm.base.math;

import cdm.base.math.MoneyBound;
import cdm.base.math.MoneyBound.MoneyBoundBuilder;
import cdm.base.math.MoneyBound.MoneyBoundBuilderImpl;
import cdm.base.math.MoneyBound.MoneyBoundImpl;
import cdm.base.math.meta.MoneyBoundMeta;
import cdm.observable.asset.Money;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * The money bound is defined as a money amount and whether the bound is inclusive.
 * @version ${project.version}
 */
@RosettaDataType(value="MoneyBound", builder=MoneyBound.MoneyBoundBuilderImpl.class, version="${project.version}")
public interface MoneyBound extends RosettaModelObject {

	MoneyBoundMeta metaData = new MoneyBoundMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The money amount to be used as the bound, e.g. 1,000 USD.
	 */
	Money getMoney();
	/**
	 * Whether the money amount bound is inclusive, e.g. for a lower bound, false would indicate greater than, whereas true would indicate greater than or equal to.
	 */
	Boolean getInclusive();

	/*********************** Build Methods  ***********************/
	MoneyBound build();
	
	MoneyBound.MoneyBoundBuilder toBuilder();
	
	static MoneyBound.MoneyBoundBuilder builder() {
		return new MoneyBound.MoneyBoundBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MoneyBound> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MoneyBound> getType() {
		return MoneyBound.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("money"), processor, Money.class, getMoney());
		processor.processBasic(path.newSubPath("inclusive"), Boolean.class, getInclusive(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface MoneyBoundBuilder extends MoneyBound, RosettaModelObjectBuilder {
		Money.MoneyBuilder getOrCreateMoney();
		Money.MoneyBuilder getMoney();
		MoneyBound.MoneyBoundBuilder setMoney(Money money);
		MoneyBound.MoneyBoundBuilder setInclusive(Boolean inclusive);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("money"), processor, Money.MoneyBuilder.class, getMoney());
			processor.processBasic(path.newSubPath("inclusive"), Boolean.class, getInclusive(), this);
		}
		

		MoneyBound.MoneyBoundBuilder prune();
	}

	/*********************** Immutable Implementation of MoneyBound  ***********************/
	class MoneyBoundImpl implements MoneyBound {
		private final Money money;
		private final Boolean inclusive;
		
		protected MoneyBoundImpl(MoneyBound.MoneyBoundBuilder builder) {
			this.money = ofNullable(builder.getMoney()).map(f->f.build()).orElse(null);
			this.inclusive = builder.getInclusive();
		}
		
		@Override
		@RosettaAttribute("money")
		public Money getMoney() {
			return money;
		}
		
		@Override
		@RosettaAttribute("inclusive")
		public Boolean getInclusive() {
			return inclusive;
		}
		
		@Override
		public MoneyBound build() {
			return this;
		}
		
		@Override
		public MoneyBound.MoneyBoundBuilder toBuilder() {
			MoneyBound.MoneyBoundBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MoneyBound.MoneyBoundBuilder builder) {
			ofNullable(getMoney()).ifPresent(builder::setMoney);
			ofNullable(getInclusive()).ifPresent(builder::setInclusive);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MoneyBound _that = getType().cast(o);
		
			if (!Objects.equals(money, _that.getMoney())) return false;
			if (!Objects.equals(inclusive, _that.getInclusive())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (money != null ? money.hashCode() : 0);
			_result = 31 * _result + (inclusive != null ? inclusive.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MoneyBound {" +
				"money=" + this.money + ", " +
				"inclusive=" + this.inclusive +
			'}';
		}
	}

	/*********************** Builder Implementation of MoneyBound  ***********************/
	class MoneyBoundBuilderImpl implements MoneyBound.MoneyBoundBuilder {
	
		protected Money.MoneyBuilder money;
		protected Boolean inclusive;
	
		public MoneyBoundBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("money")
		public Money.MoneyBuilder getMoney() {
			return money;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateMoney() {
			Money.MoneyBuilder result;
			if (money!=null) {
				result = money;
			}
			else {
				result = money = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("inclusive")
		public Boolean getInclusive() {
			return inclusive;
		}
		
	
		@Override
		@RosettaAttribute("money")
		public MoneyBound.MoneyBoundBuilder setMoney(Money money) {
			this.money = money==null?null:money.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("inclusive")
		public MoneyBound.MoneyBoundBuilder setInclusive(Boolean inclusive) {
			this.inclusive = inclusive==null?null:inclusive;
			return this;
		}
		
		@Override
		public MoneyBound build() {
			return new MoneyBound.MoneyBoundImpl(this);
		}
		
		@Override
		public MoneyBound.MoneyBoundBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MoneyBound.MoneyBoundBuilder prune() {
			if (money!=null && !money.prune().hasData()) money = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMoney()!=null && getMoney().hasData()) return true;
			if (getInclusive()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MoneyBound.MoneyBoundBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MoneyBound.MoneyBoundBuilder o = (MoneyBound.MoneyBoundBuilder) other;
			
			merger.mergeRosetta(getMoney(), o.getMoney(), this::setMoney);
			
			merger.mergeBasic(getInclusive(), o.getInclusive(), this::setInclusive);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MoneyBound _that = getType().cast(o);
		
			if (!Objects.equals(money, _that.getMoney())) return false;
			if (!Objects.equals(inclusive, _that.getInclusive())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (money != null ? money.hashCode() : 0);
			_result = 31 * _result + (inclusive != null ? inclusive.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MoneyBoundBuilder {" +
				"money=" + this.money + ", " +
				"inclusive=" + this.inclusive +
			'}';
		}
	}
}
